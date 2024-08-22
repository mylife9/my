<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="司机姓名" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入司机姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="司机手机号" prop="driverPhone">
        <el-input
          v-model="queryParams.driverPhone"
          placeholder="请输入司机手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出生年月日" prop="driverBirthday">
        <el-date-picker clearable
          v-model="queryParams.driverBirthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出生年月日">
        </el-date-picker>
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
          v-hasPermi="['driver:driveruser:add']"
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
          v-hasPermi="['driver:driveruser:edit']"
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
          v-hasPermi="['driver:driveruser:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['driver:driveruser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="driveruserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="司机注册地行政区划代码" align="center" prop="address" />
      <el-table-column label="司机姓名" align="center" prop="driverName" />
      <el-table-column label="司机手机号" align="center" prop="driverPhone" />
      <el-table-column label="性别" align="center" prop="driverGender" />
<!--        <template slot-scope="scope">-->
<!--          {{driverGenderMap[scope.row.driverGender]}}-->
<!--        </template>-->
      <el-table-column label="出生年月日" align="center" prop="driverBirthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.driverBirthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="驾驶员民族" align="center" prop="driverNation" />
      <el-table-column label="联系地址" align="center" prop="driverContactAddress" />
      <el-table-column label="司机状态" align="center" prop="state" >
      <template slot-scope="scope">
        {{stateMap[scope.row.state]}}
      </template>
      </el-table-column>
      <el-table-column label="身份证正面图片" align="center" prop="cardFrontImage" >
        <template slot-scope="scope">
<!--          <el-image :src="scope.row.cardFrontImage"></el-image>-->
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.cardFrontImage"
          ></el-image>
        </template>
      </el-table-column>
<!--      <el-table-column label="人脸照片" align="center" prop="facePhoto" />-->
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          {{statusMap[scope.row.state]}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            v-hasPermi="['driver:car:remove']"-->
<!--            v-if="scope.row.state == 0"-->
<!--            @click="handleApprove(scope.row)">审批</el-button> -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-hasPermi="['driver:car:remove']"
            @click="handleApprove(scope.row)">查看詳情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['driver:driveruser:remove']"
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

    <!-- 添加或修改司机入驻对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="司机注册地行政区划代码" prop="address">
          <el-input v-model="form.address" placeholder="请输入司机注册地行政区划代码" />
        </el-form-item>
        <el-form-item label="司机姓名" prop="driverName">
          <el-input v-model="form.driverName" placeholder="请输入司机姓名" />
        </el-form-item>
        <el-form-item label="司机手机号" prop="driverPhone">
          <el-input v-model="form.driverPhone" placeholder="请输入司机手机号" />
        </el-form-item>
        <el-form-item label="性别" prop="driverGender">
          <el-input v-model="form.driverGender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="出生年月日" prop="driverBirthday">
          <el-date-picker clearable
            v-model="form.driverBirthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出生年月日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="驾驶员民族" prop="driverNation">
          <el-input v-model="form.driverNation" placeholder="请输入驾驶员民族" />
        </el-form-item>
        <el-form-item label="联系地址" prop="driverContactAddress">
          <el-input v-model="form.driverContactAddress" placeholder="请输入联系地址" />
        </el-form-item>
        <el-form-item label="机动车驾驶证号" prop="licenseId">
          <el-input v-model="form.licenseId" placeholder="请输入机动车驾驶证号" />
        </el-form-item>
        <el-form-item label="初次领取驾驶证日期" prop="getDriverLicenseDate">
          <el-date-picker clearable
            v-model="form.getDriverLicenseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择初次领取驾驶证日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="驾驶证有效期起" prop="driverLicenseOn">
          <el-date-picker clearable
            v-model="form.driverLicenseOn"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择驾驶证有效期起">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="驾驶证有效期止" prop="driverLicenseOff">
          <el-date-picker clearable
            v-model="form.driverLicenseOff"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择驾驶证有效期止">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否巡游出租汽车" prop="taxiDriver">
          <el-input v-model="form.taxiDriver" placeholder="请输入是否巡游出租汽车" />
        </el-form-item>
        <el-form-item label="网络预约出租汽车驾驶员资格证号" prop="certificateNo">
          <el-input v-model="form.certificateNo" placeholder="请输入网络预约出租汽车驾驶员资格证号" />
        </el-form-item>
        <el-form-item label="网络预约出租汽车驾驶员发证机构" prop="networkCarIssueOrganization">
          <el-input v-model="form.networkCarIssueOrganization" placeholder="请输入网络预约出租汽车驾驶员发证机构" />
        </el-form-item>
        <el-form-item label="资格证发证日期" prop="networkCarIssueDate">
          <el-date-picker clearable
            v-model="form.networkCarIssueDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择资格证发证日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="初次领取资格证日期" prop="getNetworkCarProofDate">
          <el-date-picker clearable
            v-model="form.getNetworkCarProofDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择初次领取资格证日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="资格证有效起始日期" prop="networkCarProofOn">
          <el-date-picker clearable
            v-model="form.networkCarProofOn"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择资格证有效起始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="资格证有效截止日期" prop="networkCarProofOff">
          <el-date-picker clearable
            v-model="form.networkCarProofOff"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择资格证有效截止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报备日期" prop="registerDate">
          <el-date-picker clearable
            v-model="form.registerDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报备日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="驾驶员合同" prop="contractCompany">
          <el-input v-model="form.contractCompany" placeholder="请输入驾驶员合同" />
        </el-form-item>
        <el-form-item label="合同" prop="contractOn">
          <el-date-picker clearable
            v-model="form.contractOn"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择合同">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同有效期止" prop="contractOff">
          <el-date-picker clearable
            v-model="form.contractOff"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择合同有效期止">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="司机状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入司机状态" />
        </el-form-item>
        <el-form-item label="创建时间" prop="gmtCreate">
          <el-date-picker clearable
            v-model="form.gmtCreate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="gmtModified">
          <el-date-picker clearable
            v-model="form.gmtModified"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="身份证正面图片" prop="cardFrontImage">
          <image-upload v-model="form.cardFrontImage"/>
        </el-form-item>
        <el-form-item label="身份证反面图片" prop="cardReverseImage">
          <image-upload v-model="form.cardReverseImage"/>
        </el-form-item>
        <el-form-item label="驾驶证照片" prop="driverPhoto">
          <el-input v-model="form.driverPhoto" placeholder="请输入驾驶证照片" />
        </el-form-item>
        <el-form-item label="人脸照片" prop="facePhoto">
          <el-input v-model="form.facePhoto" placeholder="请输入人脸照片" />
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
<!--        <el-form-item label="司机注册地行政区划代码" prop="address" >-->
<!--          {{form.address}}-->
<!--        </el-form-item>-->
        <el-form-item label="司机姓名" prop="driverName">
          {{form.driverName}}
        </el-form-item>
        <el-form-item label="司机手机号" prop="driverPhone">
          {{form.driverPhone}}
        </el-form-item>
        <el-form-item label="性别" prop="driverGender">
          {{form.driverGender}}
        </el-form-item>

        <el-form-item label="驾驶员民族" prop="driverNation">
          {{form.driverNation}}
        </el-form-item>
        <el-form-item label="联系地址" prop="driverContactAddress">
          {{form.driverContactAddress}}
        </el-form-item>


        <el-form-item label="网络预约出租汽车驾驶员资格证号" prop="certificateNo">
          {{form.certificateNo}}
        </el-form-item>
        <el-form-item label="网络预约出租汽车驾驶员发证机构" prop="networkCarIssueOrganization">
          {{form.networkCarIssueOrganization}}
        </el-form-item>


        <el-form-item label="身份证正面图片" prop="cardFrontImage">
          <el-image :src="form.cardFrontImage"></el-image>


<!--          {{form.cardFrontImage}}-->
        </el-form-item>

      </el-form>
    <el-button type="primary" @click="userSccess(1)">通过</el-button>
    <el-button type="danger" @click="userSccess(2)">驳回</el-button>
        <el-button @click="cancel">取 消</el-button>

  </span>
    </el-dialog>

    <!-- 审批车辆管理对话框 -->
<!--    <el-dialog-->
<!--      title="审批司机入驻管理"-->
<!--      :visible.sync="dialogVisible"-->
<!--      width="30%">-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="司机注册地行政区划代码" prop="address" >-->
<!--          <el-input :disabled="true" v-model="form.address" placeholder="请输入司机注册地行政区划代码" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="司机姓名" prop="driverName">-->
<!--          <el-input :disabled="true" v-model="form.driverName" placeholder="请输入司机姓名" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="司机手机号" prop="driverPhone">-->
<!--          <el-input :disabled="true" v-model="form.driverPhone" placeholder="请输入司机手机号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="性别" prop="driverGender">-->
<!--          <el-input :disabled="true" v-model="form.driverGender" placeholder="请输入性别" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="驾驶员民族" prop="driverNation">-->
<!--          <el-input :disabled="true" v-model="form.driverNation" placeholder="请输入驾驶员民族" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="联系地址" prop="driverContactAddress">-->
<!--          <el-input :disabled="true" v-model="form.driverContactAddress" placeholder="请输入联系地址" />-->
<!--        </el-form-item>-->


<!--        <el-form-item label="网络预约出租汽车驾驶员资格证号" prop="certificateNo">-->
<!--          <el-input :disabled="true" v-model="form.certificateNo" placeholder="请输入网络预约出租汽车驾驶员资格证号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="网络预约出租汽车驾驶员发证机构" prop="networkCarIssueOrganization">-->
<!--          <el-input :disabled="true" v-model="form.networkCarIssueOrganization" placeholder="请输入网络预约出租汽车驾驶员发证机构" />-->
<!--        </el-form-item>-->


<!--        <el-form-item label="身份证正面图片" prop="carPicture">-->
<!--          <el-image :src="form.cardFrontImage"/>-->
<!--&lt;!&ndash;           <el-image&ndash;&gt;-->
<!--&lt;!&ndash;             style="width: 100px; height: 100px"&ndash;&gt;-->
<!--&lt;!&ndash;             :src="form.cardFrontImage"&ndash;&gt;-->
<!--&lt;!&ndash;             ></el-image>&ndash;&gt;-->
<!--        </el-form-item>-->

<!--      </el-form>-->
<!--    <el-button type="primary" @click="userSccess(1)">通过</el-button>-->
<!--    <el-button type="danger" @click="userSccess(2)">驳回</el-button>-->
<!--  </span>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listDriveruser, getDriveruser,userSuccessUpdata, delDriveruser, addDriveruser, updateDriveruser } from "@/api/approve/driveruser";
import {carSuccessUpdata, getCar} from "@/api/approve/car";

export default {
  name: "Driveruser",
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
      // 司机入驻表格数据
      driveruserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dialogVisible:false,

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        driverName: null,
        driverPhone: null,
        driverBirthday: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      statusMap:{
        0:"待审批",
        1:"通过",
        2:"驳回"
      },
      // driverGenderMap:{
      //   0:"男",
      //   1:"女",
      // },
      stateMap:{
        0:"待审核",
        1:"通过",
        2:"驳回",
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {

    /** 审批按钮操作 */
    handleApprove(row) {
      this.id=row.id
      this.dialogVisible = true;
      this.reset();
      const id = row.id || this.ids
      getDriveruser(id).then(response => {
        this.form = response.data;
      });
    },

    // 通过按钮
    userSccess(status){
      var data={
        state:status,
        id:this.id
      }
      userSuccessUpdata(data).then(res=>{
        this.dialogVisible=false
        this.getList()

      })
    },


    /** 查询司机入驻列表 */
    getList() {
      this.loading = true;
      listDriveruser(this.queryParams).then(response => {
        this.driveruserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.dialogVisible = false;
      this.open=false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        address: null,
        driverName: null,
        driverPhone: null,
        driverGender: null,
        driverBirthday: null,
        driverNation: null,
        driverContactAddress: null,
        licenseId: null,
        getDriverLicenseDate: null,
        driverLicenseOn: null,
        driverLicenseOff: null,
        taxiDriver: null,
        certificateNo: null,
        networkCarIssueOrganization: null,
        networkCarIssueDate: null,
        getNetworkCarProofDate: null,
        networkCarProofOn: null,
        networkCarProofOff: null,
        registerDate: null,
        commercialType: null,
        contractCompany: null,
        contractOn: null,
        contractOff: null,
        state: null,
        gmtCreate: null,
        gmtModified: null,
        cardFrontImage: null,
        cardReverseImage: null,
        driverPhoto: null,
        facePhoto: null
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
      this.title = "添加司机入驻";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDriveruser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改司机入驻";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDriveruser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDriveruser(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除司机入驻编号为"' + ids + '"的数据项？').then(function() {
        return delDriveruser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('driver/driveruser/export', {
        ...this.queryParams
      }, `driveruser_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
