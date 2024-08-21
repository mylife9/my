<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车辆型号" prop="brand">
        <el-input
          v-model="queryParams.brand"
          placeholder="请输入车辆型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆品牌" prop="model">
        <el-select v-model="queryParams.model" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="车辆拼音" prop="pinyinBrand">
        <el-input
          v-model="queryParams.pinyinBrand"
          placeholder="请输入车辆拼音"
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
          v-hasPermi="['driver:driver:add']"
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
          v-hasPermi="['driver:driver:edit']"
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
          v-hasPermi="['driver:driver:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['driver:driver:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="driverList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="车辆所在城市" align="center" prop="address" />
      <el-table-column label="车辆号牌" align="center" prop="vehicleNo" />
      <el-table-column label="车牌颜色" align="center" prop="plateColor" />
      <el-table-column label="核定载客位" align="center" prop="seats" />
      <el-table-column label="车辆品牌" align="center" prop="model" />
      <el-table-column label="车辆型号" align="center" prop="brand" />
      <el-table-column label="车辆类型" align="center" prop="vehicleType" />
      <el-table-column label="车辆所有人" align="center" prop="ownerName" />
      <el-table-column label="车辆颜色" align="center" prop="vehicleColor" />
      <el-table-column label="发动机号" align="center" prop="engineId" />
      <el-table-column label="车辆vin识别代码" align="center" prop="vin" />
      <el-table-column label="车辆注册日期" align="center" prop="certifyDateA" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.certifyDateA, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="燃料类型" align="center" prop="fueType" >
        <template slot-scope="scope">
          <span>{{fueTypeMap[scope.row.fueType]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="发动机排量" align="center" prop="engineDisplace" />
      <el-table-column label="车辆运输证发证机构" align="center" prop="transAgency" />
      <el-table-column label="车辆经验区域" align="center" prop="transArea" />
      <el-table-column label="车辆运输证有效期起" align="center" prop="transDateStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.transDateStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆运输证有效期止" align="center" prop="transDateEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.transDateEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆初次登记日期" align="center" prop="certifyDateB" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.certifyDateB, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆的检修状态" align="center" prop="fixState" >
        <template slot-scope="scope">
          <span>{{statusMap[scope.row.fixState]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="下次年检时间" align="center" prop="nextFixDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextFixDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年度审验状态" align="center" prop="checkState" >
        <template slot-scope="scope">
          <span>{{checkStateMap[scope.row.checkState]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="发票打印设备序列号" align="center" prop="feePrintId" />
      <el-table-column label="卫星定位装置品牌" align="center" prop="gpsBrand" />
      <el-table-column label="卫星型号" align="center" prop="gpsModel" />
      <el-table-column label="卫星定位设备安装日期" align="center" prop="gpsInstallDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gpsInstallDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报备日期" align="center" prop="registerDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务类型" align="center" prop="commercialType" >
        <template slot-scope="scope">
          <span>{{commercialTypeMap[scope.row.commercialType]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="运价编码 关联计价规则" align="center" prop="fareType" />
      <el-table-column label="状态" align="center" prop="state" >
      <template slot-scope="scope">
        <span>{{stateMap[scope.row.state]}}</span>
      </template>
      </el-table-column>
      <el-table-column label="终端Id" align="center" prop="tid" />
      <el-table-column label="轨迹ID" align="center" prop="trid" />

      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="gmtModified" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆图片" align="center" prop="carPicture" />
      <el-table-column label="持有方" align="center" prop="owerStatus" >
        <template slot-scope="scope">
          <span>{{owerStatusMap[scope.row.owerStatus]}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['driver:driver:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['driver:driver:remove']"
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

    <!-- 添加或修改车辆对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆所在城市" prop="address">
          <el-cascader
            size="large"
            :options="cityOptions"
            v-model="form.address"
            @change="handleChange"
            :props="{'label':'addressName','value':'addressCode'}"
            >
          </el-cascader>
        </el-form-item>
        <el-form-item label="车辆号牌" prop="vehicleNo">
          <el-input
            id="licensePlate"
            v-model="form.vehicleNo"
            @input="validateLicensePlate"
            :class="{ invalid: !isValid }"
            placeholder="请输入车辆号牌"
          />
          <span v-if="!isValid" class="error-message">{{ errorMessage }}</span>
        </el-form-item>
        <el-form-item label="车牌颜色" prop="plateColor">
            <el-select v-model="form.plateColor" placeholder="请选择">
              <el-option
                v-for="item in options4"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="核定载客位" prop="seats">
          <el-input
            type="number"
            id="licensePlate"
            v-model="form.seats"
            @input="validateSeats"
            :class="{ invalid: !seatError   }"
            placeholder="请输入核定载客位"
          />
          <span v-if="seatError " class="error-message">{{ seatError  }}</span>
        </el-form-item>

        <el-form-item label="车辆品牌" prop="model">
          <!--<el-input v-model="form.model" placeholder="请输入车辆品牌" />-->
          <el-select v-model="form.model" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆型号" prop="brand">
          <el-select v-model="form.brand" placeholder="请选择">
            <el-option
              v-for="item in options5"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆所有人" prop="ownerName">
          <el-input v-model="form.ownerName" placeholder="请输入车辆所有人" />
        </el-form-item>
        <el-form-item label="车辆颜色" prop="vehicleColor">
          <el-select v-model="form.vehicleColor" placeholder="请选择">
            <el-option
              v-for="item in options3"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发动机号" prop="engineId">

          <el-input
            type="text"
            id="engineNumber"
            v-model="form.engineId"
            @input="validateEngineNumber"
            :class="{ invalid: !engineNumberError    }"
            placeholder="请输入发动机号"
          />
          <span v-if="engineNumberError " class="error-message">{{ engineNumberError  }}</span>
        </el-form-item>
        <el-form-item label="车辆vin识别代码" prop="vin">
          <el-input v-model="form.vin" placeholder="请输入车辆vin识别代码" />
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
        <el-form-item label="车辆运输证发证机构" prop="transAgency">
          <el-input v-model="form.transAgency" placeholder="请输入车辆运输证发证机构" />
        </el-form-item>
        <el-form-item label="车辆经验区域" prop="transArea">
          <el-input v-model="form.transArea" placeholder="请输入车辆经验区域" />
        </el-form-item>
        <el-form-item label="车辆运输证有效期起" prop="transDateStart">
          <el-date-picker clearable
            v-model="form.transDateStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择车辆运输证有效期起">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆运输证有效期止" prop="transDateEnd">
          <el-date-picker clearable
            v-model="form.transDateEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择车辆运输证有效期止">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆初次登记日期" prop="certifyDateB">
          <el-date-picker clearable
            v-model="form.certifyDateB"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择车辆初次登记日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆的检修状态" prop="fixState">
          <el-select v-model="form.fixState" placeholder="请选择">
            <el-option
              v-for="item in options2"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="下次年检时间" prop="nextFixDate">
          <el-date-picker clearable
            v-model="form.nextFixDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择下次年检时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发票打印设备序列号" prop="feePrintId">
          <el-input v-model="form.feePrintId" placeholder="请输入发票打印设备序列号" />
        </el-form-item>
        <el-form-item label="卫星定位装置品牌" prop="gpsBrand">
          <el-input v-model="form.gpsBrand" placeholder="请输入卫星定位装置品牌" />
        </el-form-item>
        <el-form-item label="卫星型号" prop="gpsModel">
          <el-input v-model="form.gpsModel" placeholder="请输入卫星型号" />
        </el-form-item>
        <el-form-item label="卫星定位设备安装日期" prop="gpsInstallDate">
          <el-date-picker clearable
            v-model="form.gpsInstallDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择卫星定位设备安装日期">
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
        <el-form-item label="终端Id" prop="tid">
          <el-input v-model="form.tid" placeholder="请输入终端Id" />
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
        <el-form-item label="车辆图片" prop="carPicture">
          <el-input v-model="form.carPicture" placeholder="请输入车辆图片" />


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
import { listDriver, getDriver, delDriver, addDriver, updateDriver,addressList } from "@/api/driver/driver";
import 'element-ui/lib/theme-chalk/index.css';

export default {
  name: "Driver",
  data() {
    return {
      isValid: true, // 用于表示输入是否合法
      errorMessage: '' ,// 错误提示信息
      seatError:null,
      engineNumber: '',
      engineNumberError: null,
      options1: [
        {
        value: '110000',
      label: '北京',
      children: [
      {
        value: '110100',
        label: '北京市',
        children: [
          { value: '110101', label: '东城区' },
          { value: '110102', label: '西城区' },
          { value: '110105', label: '朝阳区' }
        ]
      }
    ]
  },
    {
      value: '310000',
        label: '上海',
      children: [
      {
        value: '310100',
        label: '上海市',
        children: [
          { value: '310101', label: '黄浦区' },
          { value: '310104', label: '徐汇区' },
          { value: '310105', label: '长宁区' }
        ]
      }
    ]
    },
    {
      value: '440000',
        label: '广东',
      children: [
      {
        value: '440100',
        label: '广州市',
        children: [
          { value: '440103', label: '荔湾区' },
          { value: '440106', label: '天河区' },
          { value: '440111', label: '白云区' }
        ]
      },
      {
        value: '440300',
        label: '深圳市',
        children: [
          { value: '440305', label: '南山区' },
          { value: '440304', label: '福田区' },
          { value: '440303', label: '罗湖区' }
        ]
      }
    ]
    }
  ],

      selectedOptions: [],
      options: [{
        value: '吉利',
        label: '吉利'
      }, {
        value: '几何',
        label: '几何'
      }, {
        value: '沃尔沃',
        label: '沃尔沃'
      }, {
        value: '枫叶',
        label: '枫叶'
      }],
      options5: [{
        value: '吉利帝豪EV',
        label: '吉利帝豪EV'
      }, {
        value: '吉利博瑞',
        label: '吉利博瑞'
      }, {
        value: '几何A ',
        label: '几何A '
      }, {
        value: '几何c ',
        label: '几何c '
      }, {
        value: '沃尔沃XC60',
        label: '沃尔沃XC60'
      }],
      options2: [{
        value: 0,
        label: '未检修'
      }, {
        value: 1,
        label: '已检修'
      }, {
        value: 2,
        label: '未知'
      }],
      //车辆颜色
      options3: [{
        value: 0,
        label: '黑色'
      }, {
        value: 1,
        label: '白色'
      }, {
        value: 2,
        label: '其他'
      }],
      //车牌颜色

      options4: [{
        value: 0,
        label: '白色'
      }, {
        value: 1,
        label: '蓝色'
      }, {
        value: 2,
        label: '绿色'
      },{
        value: 2,
        label: '黄色'
      },{
        value: 2,
        label: '黑色'
      },
      ],
      statusMap:
        {
          '0':"未检修",
          '1':"已检修",
          '2':"未知"
        },
      fueTypeMap:
        {
        '1' :"汽油",
        '2':"柴油",
        '3':"天然气",
        '4':"液化气",
        '5':"电动",
        '6':"其他",

        },
      checkStateMap:
        {
          '0':"未年审",
          '1':"年审合格",
          '2':"年审不合格"
        }, owerStatusMap:
        {
          '0':"平台",
          '1':"司机个人",
        },
      stateMap:
        {
          '0':"有效",
          '1':"失效",
        },
      commercialTypeMap:
        {
          '1':"网络预约出租车",
          '2':"巡游出租车",
          '3':"私人小客车合乘",
        },


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
      // 车辆表格数据
      driverList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleNo: null,
        brandId: null,
        model: null,
        vehicleType: null,
        pinyinCar:null,
        pinyinBrand:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      cityOptions:[]
    };
  },
  created() {
    this.getList();
   this.addressQuery();

  },
  methods: {

    addressQuery(){
      addressList().then(res=>{
        this.cityOptions=res.rows
      });
    },
    /** 查询车辆列表 */
    getList() {
      this.loading = true;
      listDriver(this.queryParams).then(response => {
        this.driverList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleChange (value) {
      console.log('选中的行政代码:', value);
      const selectedCode = value[value.length - 1];
      console.log('需要存储的行政代码:', selectedCode);
      // 在这里处理存储逻辑
      this.form.address = selectedCode
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
        address: null,
        vehicleNo: null,
        plateColor: null,
        seats: null,
        brand: null,
        brandId: null,
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
        owerStatus: null,
        brandName:null,
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
      this.title = "添加车辆";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDriver(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆";
      });
    },
    validateLicensePlate() {
      // 定义车辆号牌的正则表达式
      const licensePlatePattern = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{5}$/;

      // 校验用户输入是否符合正则表达式
      if (this.form.vehicleNo === '') {
        this.isValid = false;
        this.errorMessage = '请输入车辆号牌';
      } else if (!licensePlatePattern.test(this.form.vehicleNo)) {
        this.isValid = false;
        this.errorMessage = '号牌格式不正确';
      } else {
        this.isValid = true;
        this.errorMessage = '';
      }
    },
    validateSeats() {
      const minSeats = 1; // 最小载客位
      const maxSeats = 7; // 最大载客位

      if (this.form.seats < minSeats || this.form.seats > maxSeats) {
        this.seatError  = `核定载客位必须在 ${minSeats} 到 ${maxSeats} 之间`;
      } else {
        this.seatError  = null; // 清除错误信息
      }
    },
    validateEngineNumber() {
      const engineNumberPattern = /^[A-Z0-9]{8,17}$/; // 发动机号的正则表达式，可以根据实际需求调整

      if (!engineNumberPattern.test(this.form.engineId)) {
        this.engineNumberError = '请输入有效的发动机号（8到17位，包含字母和数字）';
      } else {
        this.engineNumberError = null; // 清除错误信息
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDriver(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDriver(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除车辆编号为"' + ids + '"的数据项？').then(function() {
        return delDriver(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('driver/driver/export', {
        ...this.queryParams
      }, `driver_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.invalid {
  border-color: red;
}
.error-message {
  color: red;
}
</style>
