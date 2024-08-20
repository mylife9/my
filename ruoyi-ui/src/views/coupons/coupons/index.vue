<template>
  <div class="app-container">
    <el-form :model="queryParams" class="demo-form-inline" ref="queryForm" size="small" :inline="true"
             v-show="showSearch" width="100px">
      <el-form-item label="优惠券的名称" prop="couponsName">
        <el-input
          v-model="queryParams.couponsName"
          placeholder="请输入优惠券的名称"
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
          v-hasPermi="['coupons:coupons:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['coupons:coupons:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['coupons:coupons:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['coupons:coupons:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="couponsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="优惠券编号" align="center" prop="couponsNumber"/>
      <el-table-column label="优惠券名称" align="center" prop="couponsName"/>
      <el-table-column label="面值" align="center" prop="couponsAmount"/>
      <el-table-column label="最低消费" align="center" prop="couponHold"/>
      <el-table-column label="类型" align="center" prop="typeName">

      </el-table-column>
      <el-table-column label="数量" align="center" prop="receiveCount"/>
      <el-table-column label="创建时间" align="center" prop="couponsCreateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.couponsCreateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="过期时间" align="center" prop="couponsExpirationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.couponsExpirationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态 " align="center" prop="couponsStatus">
        <template slot-scope="scope">
          {{ mapStatus[scope.row.couponsStatus] }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['coupons:coupons:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"

            @click="handleDelete(scope.row)"
            v-hasPermi="['coupons:coupons:remove']"
          >删除
          </el-button>
          <el-button
            v-if="scope.row.couponsStatus==0"
            size="mini"
            type="text"
            @click="handleCoupons(scope.row,1)"
          >开始发券
          </el-button>

<!--          <el-button
            v-if="scope.row.couponsStatus==1"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleCoupons(scope.row,0)"
          >停止发卷
          </el-button>-->


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

    <!-- 添加或修改优惠券对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="130px">
        <el-form-item label="优惠券的编号" prop="couponsNumber">
          <el-input v-model="form.couponsNumber" placeholder="请输入优惠券的编号"/>
        </el-form-item>
        <el-form-item label="优惠券的名称" prop="couponsName">
          <el-input v-model="form.couponsName" placeholder="请输入优惠券的名称"/>
        </el-form-item>

        <el-form-item label="优惠券的类型" prop="couponsType">
          <el-select v-model="form.couponsType" placeholder="请选择优惠券的类型">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.typeName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="优惠券的面值" prop="couponsAmount">
          <el-input v-model="form.couponsAmount" placeholder="请输入优惠券的面值"/>
        </el-form-item>
        <el-form-item label="优惠券的最低消费" prop="couponHold">
          <el-input v-model="form.couponHold" placeholder="请输入优惠券的最低消费"/>
        </el-form-item>
        <el-form-item label="发出的数量" prop="receiveCount">
          <el-input v-model="form.receiveCount" placeholder="请输入发出的数量"/>
        </el-form-item>
        <el-form-item label="优惠券的创建时间" prop="couponsCreateDate">
          <el-date-picker clearable
                          v-model="form.couponsCreateDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择优惠券的创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优惠券的过期时间" prop="couponsExpirationDate">
          <el-date-picker clearable
                          v-model="form.couponsExpirationDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择优惠券的过期时间">
          </el-date-picker>
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
import {
  addCoupons,
  listType,
  delCoupons,
  getCoupons,
  listCoupons,
  startCoupons, edit
} from "@/api/coupons/coupons";

export default {
  name: "Coupons",
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
      // 优惠券表格数据
      couponsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数

      mapStatus: {
        0: "停止发放",
        1: "正在发放"
      },


      queryParams: {
        pageNum: 1,
        pageSize: 10,
        couponsName: null,
        couponsType: null,
        couponsStatus: null
      },
      //下拉框
      options: [],

      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();


  },
  methods: {
    //发卷按钮
    handleCoupons(row, status) {
      var data = {
        id: row.id,
        status: status
      }
      startCoupons(data).then(res => {
        console.log(res);
        this.$message(res);
        this.getList()
      })
    },
    /** 查询优惠券列表 */
    getList() {
      this.loading = true;
      listCoupons(this.queryParams).then(response => {
        console.log(response)
        this.couponsList = response.rows;
        this.total = response.total;
        this.loading = false;


      });
    },

    //下拉框 类型
    getCouponsTypeList() {
      listType().then(res => {
        console.log(res)
        this.options = res.rows
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        couponsNumber: null,
        couponsName: null,
        couponsAmount: null,
        couponHold: null,
        couponsType: null,
        receiveCount: null,
        couponsCreateDate: null,
        couponsExpirationDate: null,
        couponsStatus: null
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加优惠券";
      this.getCouponsTypeList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.getCouponsTypeList();
      this.reset();
      const id = row.id || this.ids
      getCoupons(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改优惠券";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            edit(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCoupons(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除优惠券编号为"' + ids + '"的数据项？').then(function () {
        return delCoupons(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('coupons/coupons/export', {
        ...this.queryParams
      }, `coupons_${new Date().getTime()}.xlsx`)
    },


  }
};
</script>
