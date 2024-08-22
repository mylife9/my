<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆类型" prop="modelName">
        <el-input
          v-model="queryParams.modelName"
          placeholder="请输入车辆类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册日期" prop="registrationDate">
        <el-date-picker clearable
          v-model="queryParams.registrationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择注册日期">
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
          v-hasPermi="['driver:licensesd:add']"
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
          v-hasPermi="['driver:licensesd:edit']"
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
          v-hasPermi="['driver:licensesd:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['driver:licensesd:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="licensesdList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="行驶证主键" align="center" prop="id" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="车辆类型" align="center" prop="modelName" />
      <el-table-column label="车辆持有人" align="center" prop="owner" />
      <el-table-column label="性质" align="center" prop="nature" />
      <el-table-column label="车辆型号" align="center" prop="modelNumber" />
      <el-table-column label="车辆识别代码" align="center" prop="identificationCode" />
      <el-table-column label="发动机号码" align="center" prop="engineNumber" />
      <el-table-column label="注册日期" align="center" prop="registrationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registrationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发证日期" align="center" prop="issueDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.issueDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="核定人数" align="center" prop="authorizedNumber" />
      <el-table-column label="总质量" align="center" prop="grossMass" />
      <el-table-column label="整备质量" align="center" prop="qualityService" />
      <el-table-column label="外廊尺寸" align="center" prop="verandaDimension" />
      <el-table-column label="行驶证图片" align="center" prop="drivingImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.drivingImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="司机状态" align="center" prop="state" >
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
            @click="handleUpdate(scope.row)"
            v-hasPermi="['driver:licensesd:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleFind(scope.row)"
            v-hasPermi="['driver:licensesd:edit']"
          >查看詳情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['driver:licensesd:remove']"
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


    <el-dialog
      title="详情"
      :visible.sync="open2"
      width="30%">
      <span slot="footer" class="dialog-footer">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌号" prop="licensePlate">
          {{form.licensePlate}}
        </el-form-item>
        <el-form-item label="车辆类型" prop="modelName">
{{form.modelName}}
        </el-form-item>
        <el-form-item label="车辆持有人" prop="owner">
          {{form.owner}}
        </el-form-item>
        <el-form-item label="性质" prop="nature">
          {{form.nature}}
        </el-form-item>
        <el-form-item label="车辆型号" prop="modelNumber">
          {{form.modelNumber}}
        </el-form-item>
        <el-form-item label="车辆识别代码" prop="identificationCode">
          {{form.identificationCode}}

        </el-form-item>
        <el-form-item label="发动机号码" prop="engineNumber">

          {{form.engineNumber}}
        </el-form-item>
        <el-form-item label="注册日期" prop="registrationDate">

          {{form.registrationDate}}
        </el-form-item>
        <el-form-item label="发证日期" prop="issueDate">

          {{form.issueDate}}
        </el-form-item>
        <el-form-item label="核定人数" prop="authorizedNumber">
          {{form.authorizedNumber}}
        </el-form-item>
        <el-form-item label="总质量" prop="grossMass">
          {{form.grossMass}}
        </el-form-item>
        <el-form-item label="整备质量" prop="qualityService">
          {{form.qualityService}}
        </el-form-item>
        <el-form-item label="外廊尺寸" prop="verandaDimension">
          {{form.verandaDimension}}
        </el-form-item>
        <el-form-item label="行驶证图片" prop="drivingImage">
<!--          <image-upload v-model="form.drivingImage"/>-->
                    <el-image :src="form.drivingImage"/>

          <!--          {form.drivingImage}}-->
        </el-form-item>
      </el-form>
    <el-button type="primary" @click="userSccess(1)">通过</el-button>
    <el-button type="danger" @click="userSccess(2)">驳回</el-button>
        <el-button @click="cancel">取 消</el-button>

  </span>
    </el-dialog>

    <!-- 添加或修改行驶证管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌号" prop="licensePlate">
          <el-input v-model="form.licensePlate" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="车辆类型" prop="modelName">
          <el-input v-model="form.modelName" placeholder="请输入车辆类型" />
        </el-form-item>
        <el-form-item label="车辆持有人" prop="owner">
          <el-input v-model="form.owner" placeholder="请输入车辆持有人" />
        </el-form-item>
        <el-form-item label="性质" prop="nature">
          <el-input v-model="form.nature" placeholder="请输入性质" />
        </el-form-item>
        <el-form-item label="车辆型号" prop="modelNumber">
          <el-input v-model="form.modelNumber" placeholder="请输入车辆型号" />
        </el-form-item>
        <el-form-item label="车辆识别代码" prop="identificationCode">
          <el-input v-model="form.identificationCode" placeholder="请输入车辆识别代码" />
        </el-form-item>
        <el-form-item label="发动机号码" prop="engineNumber">
          <el-input v-model="form.engineNumber" placeholder="请输入发动机号码" />
        </el-form-item>
        <el-form-item label="注册日期" prop="registrationDate">
          <el-date-picker clearable
            v-model="form.registrationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择注册日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发证日期" prop="issueDate">
          <el-date-picker clearable
            v-model="form.issueDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发证日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="核定人数" prop="authorizedNumber">
          <el-input v-model="form.authorizedNumber" placeholder="请输入核定人数" />
        </el-form-item>
        <el-form-item label="总质量" prop="grossMass">
          <el-input v-model="form.grossMass" placeholder="请输入总质量" />
        </el-form-item>
        <el-form-item label="整备质量" prop="qualityService">
          <el-input v-model="form.qualityService" placeholder="请输入整备质量" />
        </el-form-item>
        <el-form-item label="外廊尺寸" prop="verandaDimension">
          <el-input v-model="form.verandaDimension" placeholder="请输入外廊尺寸" />
        </el-form-item>
        <el-form-item label="行驶证图片" prop="drivingImage">
          <image-upload v-model="form.drivingImage"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLicensesd, getLicensesd, delLicensesd,licenseSuccessUpdata, addLicensesd, updateLicensesd } from "@/api/driver/licensesd";
import {getDriver} from "@/api/driver/driver";

export default {
  name: "Licensesd",
  data() {
    return {
      state:'',
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
      // 行驶证管理表格数据
      licensesdList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licensePlate: null,
        modelName: null,
        registrationDate: null,
      },
      // 表单参数
      form: {},
      form1: {},
      // 表单校验
      rules: {
      },
      statusMap:{
        0:"待审批",
        1:"通过",
        2:"驳回"
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
      getLicensesd(id).then(response => {
        this.form1 = response.data;
      });
    },

    // 通过按钮
    userSccess(status){
      var data={
        state:status,
        id:this.id
      }
      licenseSuccessUpdata(data).then(res=>{
        this.dialogVisible=false
        this.getList()

      })
    },

    /** 查询行驶证管理列表 */
    getList() {
      this.loading = true;
      listLicensesd(this.queryParams).then(response => {
        debugger
        this.licensesdList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open2 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        licensePlate: null,
        modelName: null,
        owner: null,
        nature: null,
        modelNumber: null,
        identificationCode: null,
        engineNumber: null,
        registrationDate: null,
        issueDate: null,
        authorizedNumber: null,
        grossMass: null,
        qualityService: null,
        verandaDimension: null,
        drivingImage: null
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
      this.title = "添加行驶证管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLicensesd(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改行驶证管理";
      });
    },
    /** 修改按钮操作 */
    handleFind(row) {
      this.reset();
      const id = row.id || this.ids
      getLicensesd(id).then(response => {
        this.form = response.data;
        this.open2 = true;
        this.title = "查看详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLicensesd(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLicensesd(this.form).then(response => {
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
      this.$modal.confirm('是否确认刪除行驶证管理编号为"' + ids + '"的数据项？').then(function() {
        return delLicensesd(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('driver/licensesd/export', {
        ...this.queryParams
      }, `licensesd_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
