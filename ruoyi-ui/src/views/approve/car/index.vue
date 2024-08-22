<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车辆号牌" prop="vehicleNo">
        <el-input
          v-model="queryParams.vehicleNo"
          placeholder="请输入车辆号牌"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌名" prop="brandName">
        <el-input
          v-model="queryParams.brandName"
          placeholder="请输入品牌名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆拼音" prop="pinyinCar">
        <el-input
          v-model="queryParams.pinyinCar"
          placeholder="请输入车辆拼音"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌拼音" prop="pinyinBrand">
        <el-input
          v-model="queryParams.pinyinBrand"
          placeholder="请输入品牌拼音"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['driver:car:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['driver:car:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['driver:car:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['driver:car:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="carList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="车辆所在城市" align="center" prop="address" />
      <el-table-column label="车辆号牌" align="center" prop="vehicleNo" />
      <el-table-column label="车牌颜色" align="center" prop="plateColor" >
        <template slot-scope="scope">
          <span>{{plateColorMap[scope.row.plateColor]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="核定载客位" align="center" prop="seats" />
<!--      <el-table-column label="车辆所有人" align="center" prop="ownerName" />-->
<!--      <el-table-column label="汽车vin码" align="center" prop="vin" />-->
<!--      <el-table-column label="车辆经验区域" align="center" prop="transArea" />-->
<!--      <el-table-column label="车辆运输证有效期起" align="center" prop="transDateStart" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.transDateStart, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="品牌名" align="center" prop="brandName" />-->
<!--      <el-table-column label="品牌id" align="center" prop="brandId" />-->
<!--      <el-table-column label="持有方状态" align="center" prop="owerStatus" />-->
      <el-table-column label="车辆图片" align="center" prop="carPicture" >
        <template slot-scope="scope">
          <el-image :src="scope.row.carPicture"></el-image>
<!--          <el-image :key="scope.row.carPicture" :src="scope.row.carPicture" ></el-image>-->
<!--          <el-image v-for="url in urls" :key="url" :src="url" lazy></el-image>-->
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          {{statusMap[scope.row.state]}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-hasPermi="['driver:car:remove']"
            @click="handleApprove(scope.row)">查看详情</el-button>
          <!--            v-if="scope.row.state == 0"-->

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['driver:car:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改车辆管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆所在城市" prop="address">
          <el-input v-model="form.address" placeholder="请输入车辆所在城市" />
        </el-form-item>
<!--        <el-form-item label="车辆图片" prop="vehicleNo">-->
<!--          <el-upload-->
<!--            class="avatar-uploader"-->
<!--            action="http://localhost:8080/driver/car/upload"-->
<!--            :show-file-list="false"-->
<!--            :on-success="handleAvatarSuccess"-->
<!--            :headers="{'Authorization':token}"-->
<!--            :before-upload="beforeAvatarUpload">-->
<!--            <img v-if="form.carPicture" :src="form.carPicture" class="avatar">-->
<!--            <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->
        <el-form-item label="车辆图片" prop="cardFrontImage">
          <image-upload v-model="form.carPicture"/>
        </el-form-item>
        <el-form-item label="车辆号牌" prop="vehicleNo">
          <el-input v-model="form.vehicleNo" placeholder="请输入车辆号牌" />
        </el-form-item>
        <el-form-item label="车牌颜色" prop="plateColor">
          <el-input v-model="form.plateColor" placeholder="请输入车牌颜色" />
        </el-form-item>
        <el-form-item label="核定载客位" prop="seats">
          <el-input v-model="form.seats" placeholder="请输入核定载客位" />
        </el-form-item>
        <el-form-item label="车辆厂牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入车辆厂牌" />
        </el-form-item>
        <el-form-item label="车辆型号" prop="model">
          <el-input v-model="form.model" placeholder="请输入车辆型号" />
        </el-form-item>
        <el-form-item label="车辆所有人" prop="ownerName">
          <el-input v-model="form.ownerName" placeholder="请输入车辆所有人" />
        </el-form-item>
        <el-form-item label="车辆颜色" prop="vehicleColor">
          <el-input v-model="form.vehicleColor" placeholder="请输入车辆颜色" />
        </el-form-item>
        <el-form-item label="发动机号" prop="engineId">
          <el-input v-model="form.engineId" placeholder="请输入发动机号" />
        </el-form-item>
        <el-form-item label="汽车vin码" prop="vin">
          <el-input v-model="form.vin" placeholder="请输入汽车vin码" />
        </el-form-item>
        <el-form-item label="车辆注册日期" prop="certifyDateA">
          <el-date-picker clearable
            v-model="form.certifyDateA"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择车辆注册日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发动机排量" prop="engineDisplace">
          <el-input v-model="form.engineDisplace" placeholder="请输入发动机排量" />
        </el-form-item>
<!--        <el-form-item label="车辆运输证发证机构" prop="transAgency">-->
<!--          <el-input v-model="form.transAgency" placeholder="请输入车辆运输证发证机构" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆经验区域" prop="transArea">-->
<!--          <el-input v-model="form.transArea" placeholder="请输入车辆经验区域" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆运输证有效期起" prop="transDateStart">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.transDateStart"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择车辆运输证有效期起">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆运输证有效期止" prop="transDateEnd">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.transDateEnd"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择车辆运输证有效期止">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆初次登记日期" prop="certifyDateB">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.certifyDateB"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择车辆初次登记日期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="车辆的检修状态" prop="fixState">
          <el-input v-model="form.fixState" placeholder="请输入车辆的检修状态" />
        </el-form-item>
<!--        <el-form-item label="下次年检时间" prop="nextFixDate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.nextFixDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择下次年检时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="年度审验状态" prop="checkState">-->
<!--          <el-input v-model="form.checkState" placeholder="请输入年度审验状态" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="发票打印设备序列号" prop="feePrintId">-->
<!--          <el-input v-model="form.feePrintId" placeholder="请输入发票打印设备序列号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="卫星定位装置品牌" prop="gpsBrand">-->
<!--          <el-input v-model="form.gpsBrand" placeholder="请输入卫星定位装置品牌" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="卫星型号" prop="gpsModel">-->
<!--          <el-input v-model="form.gpsModel" placeholder="请输入卫星型号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="卫星定位设备安装日期" prop="gpsInstallDate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.gpsInstallDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择卫星定位设备安装日期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="报备日期" prop="registerDate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.registerDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择报备日期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入状态" />
        </el-form-item>
<!--        <el-form-item label="终端Id" prop="tid">-->
<!--          <el-input v-model="form.tid" placeholder="请输入终端Id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="轨迹ID" prop="trid">-->
<!--          <el-input v-model="form.trid" placeholder="请输入轨迹ID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="轨迹名称" prop="trname">-->
<!--          <el-input v-model="form.trname" placeholder="请输入轨迹名称" />-->
<!--        </el-form-item>-->
        <el-form-item label="创建时间" prop="gmtCreate">
          <el-date-picker clearable
            v-model="form.gmtCreate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="详情"
      :visible.sync="dialogVisible"
      width="30%">
      <span slot="footer" class="dialog-footer">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆所在城市" prop="address">
          {{form.address}}
        </el-form-item>
        <el-form-item label="车辆号牌" prop="vehicleNo">
          {{form.vehicleNo}}
        </el-form-item>
        <el-form-item label="车牌颜色" prop="plateColor">
          {{form.plateColor}}
        </el-form-item>
        <el-form-item label="核定载客位" prop="seats">
          {{form.seats}}
        </el-form-item>
        <el-form-item label="车辆厂牌" prop="brand">
          {{form.brand}}
        </el-form-item>
        <el-form-item label="车辆型号" prop="model">
          {{form.model}}
        </el-form-item>
<!--        <el-form-item label="车辆所有人" prop="ownerName">-->
<!--          {{form.ownerName}}-->
<!--        </el-form-item>-->


<!--        <el-form-item label="轨迹名称" prop="trname" aria-readonly="true">-->
<!--          {{form.trname}}-->

<!--        </el-form-item>-->

        <el-form-item label="车辆图片" prop="carPicture">
<!--          <image-upload v-model="form.carPicture"/>-->
          <!--          <el-upload-->
          <!--            :disabled="title=='车辆审核'"-->
          <!--            class="avatar-uploader"-->
          <!--            action="/dev-api/driver/car/upload"-->
          <!--            :show-file-list="false"-->
          <!--            :on-success="handleAvatarSuccess"-->
          <!--            :headers="{'Authorization':token}">-->
          <!--            <el-img :src="form.imageUrl" class="avatar">-->
          <!--            <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
          <!--          </el-upload>-->
                    <el-image :src="form.carPicture"/>
<!--          {{form.carPicture}}-->
        </el-form-item>
      </el-form>

    <el-button type="primary" @click="carSccess(1)">通过</el-button>
    <el-button type="danger" @click="carSccess(2)">驳回</el-button>
                <el-button @click="cancel">取 消</el-button>

  </span>
    </el-dialog>




    <!-- 审批车辆管理对话框 -->
<!--    <el-dialog-->
<!--      title="详情"-->
<!--      :visible.sync="dialogVisible"-->
<!--      width="30%">-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="车辆所在城市" prop="address">-->
<!--          <el-input :disabled="true"-->
<!--                    readonly-->
<!--                    style="border: none; background-color: transparent;"-->
<!--                    v-model="form.address"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆号牌" prop="vehicleNo">-->
<!--          <el-input :disabled="true" v-model="form.vehicleNo" placeholder="请输入车辆号牌" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车牌颜色" prop="plateColor">-->
<!--          <el-input :disabled="true" v-model="form.plateColor" placeholder="请输入车牌颜色" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="核定载客位" prop="seats">-->
<!--          <el-input :disabled="true" v-model="form.seats" placeholder="请输入核定载客位" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆厂牌" prop="brand">-->
<!--          <el-input :disabled="true" v-model="form.brand" placeholder="请输入车辆厂牌" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆型号" prop="model">-->
<!--          <el-input :disabled="true" v-model="form.model" placeholder="请输入车辆型号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="车辆所有人" prop="ownerName">-->
<!--          <el-input :disabled="true" v-model="form.ownerName" placeholder="请输入车辆所有人" />-->
<!--        </el-form-item>-->


<!--        <el-form-item label="轨迹名称" prop="trname" aria-readonly="true">-->
<!--          <el-input :disabled="true" v-model="form.trname" placeholder="请输入轨迹名称" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="车辆图片" prop="carPicture">-->
<!--&lt;!&ndash;          <image-upload v-model="form.carPicture"/>&ndash;&gt;-->
<!--&lt;!&ndash;          <el-upload&ndash;&gt;-->
<!--&lt;!&ndash;            :disabled="title=='车辆审核'"&ndash;&gt;-->
<!--&lt;!&ndash;            class="avatar-uploader"&ndash;&gt;-->
<!--&lt;!&ndash;            action="/dev-api/driver/car/upload"&ndash;&gt;-->
<!--&lt;!&ndash;            :show-file-list="false"&ndash;&gt;-->
<!--&lt;!&ndash;            :on-success="handleAvatarSuccess"&ndash;&gt;-->
<!--&lt;!&ndash;            :headers="{'Authorization':token}">&ndash;&gt;-->
<!--&lt;!&ndash;            <el-img :src="form.imageUrl" class="avatar">&ndash;&gt;-->
<!--&lt;!&ndash;            <i v-else class="el-icon-plus avatar-uploader-icon"></i>&ndash;&gt;-->
<!--&lt;!&ndash;          </el-upload>&ndash;&gt;-->
<!--                    <el-image :src="form.carPicture"/>-->
<!--        </el-form-item>-->
<!--      </el-form>-->

<!--    <el-button type="primary" @click="carSccess(1)">通过</el-button>-->
<!--    <el-button type="danger" @click="carSccess(2)">驳回</el-button>-->
<!--  </span>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listCar, getCar, delCar, addCar, updateCar,carSuccessUpdata } from "@/api/approve/car";
import Cookies from 'js-cookie'

export default {
  name: "Car",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车辆管理表格数据
      carList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dialogVisible:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleNo: null,
        brandName: null,
        pinyinCar: null,
        pinyinBrand: null,
      },
      // 表单参数
      form: {},

      // 表单校验
      rules: {
      },
      id:'',
      imageUrl:'',
      token:'',
      statusMap:{
        0:"待审批",
        1:"通过",
        2:"驳回"
      },
      plateColorMap:
        {
          0:'白色',
          1:'蓝色',
          2:'绿色',
          3:'黄色',
          4:'黑色',
        },
    };
  },
  created() {
    this.getList();
    this.token = 'Bearer '+Cookies.get("Admin-Token")
  },
  methods: {
    //上传图片
    // handleAvatarSuccess(res, file) {
    //   this.form.carPicture=res.data
    // },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isLt2M;
    },
    // 通过按钮
    carSccess(status){
      var data={
          state:status,
          id:this.id
      }
        carSuccessUpdata(data).then(res=>{
           this.dialogVisible=false
           this.getList()

        })
    },
    /** 查询车辆管理列表 */
    getList() {
      this.loading = true;
      listCar(this.queryParams).then(response => {
        this.carList = response.rows;
        this.total = response.total;
        this.loading = false;

      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.dialogVisible = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        address: null,
        vehicleNo: null,
        plateColor: null,
        seats: null,
        brand: null,
        model: null,
        vehicleType: null,
        ownerName: null,
        vehicleColor: null,
        engineId: null,
        vin: null,
        certifyDateA: null,
        fueType: null,
        engineDisplace: null,
        transAgency: null,
        transArea: null,
        transDateStart: null,
        transDateEnd: null,
        certifyDateB: null,
        fixState: null,
        nextFixDate: null,
        checkState: null,
        feePrintId: null,
        gpsBrand: null,
        gpsModel: null,
        gpsInstallDate: null,
        registerDate: null,
        commercialType: null,
        fareType: null,
        state: null,
        tid: null,
        trid: null,
        trname: null,
        gmtCreate: null,
        gmtModified: null,
        carPicture: null,
        brandName: null,
        brandId: null,
        pinyinCar: null,
        pinyinBrand: null,
        owerStatus: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆管理";
    },
    /** 审批按钮操作 */
    handleApprove(row) {
      this.id=row.id
      this.dialogVisible = true;
      this.reset();
      const id = row.id || this.ids
      getCar(id).then(response => {
        this.form = response.data;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCar(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCar(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCar(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除车辆管理编号为"' + ids + '"的数据项？').then(function() {
        return delCar(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 上传图片操作 */
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);

      if(res.code == 200){
        this.form.carPicture = res.data
      }else{
        this.$message.error(res.msg)
      }

    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('driver/car/export', {
        ...this.queryParams
      }, `car_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
