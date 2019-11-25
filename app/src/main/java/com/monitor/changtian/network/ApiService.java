package com.monitor.changtian.network;

import com.monitor.changtian.FactorsExperimenterDataBean;
import com.monitor.changtian.FinanceListBean;
import com.monitor.changtian.SchemeConsumablesBean;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.BuyWayBean;
import com.monitor.changtian.bean.CalibrateDevListBean;
import com.monitor.changtian.bean.CarDataListBean;
import com.monitor.changtian.bean.DetectionSchemeBean;
import com.monitor.changtian.bean.DetectionSchemeDataBean;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EquipStateBean;
import com.monitor.changtian.bean.EquipTypeBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.EquipsBean;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.GetSchemePriceDetailListBean;
import com.monitor.changtian.bean.GetSchemeQuotationInfoBean;
import com.monitor.changtian.bean.GetfieldsamplingDetailListBean;
import com.monitor.changtian.bean.GetqualitycontrolBean;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.MessageBean;
import com.monitor.changtian.bean.MessageListBean;
import com.monitor.changtian.bean.MoneyRecordBean;
import com.monitor.changtian.bean.NoPointsTaskDataBean;
import com.monitor.changtian.bean.PersonsDataBean;
import com.monitor.changtian.bean.PreDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;
import com.monitor.changtian.bean.ProjectTestListBean;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.bean.QueryTestRecordBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleCategoryItemsDataBean;
import com.monitor.changtian.bean.SampleDetailDataBean;
import com.monitor.changtian.bean.SampleDetailsAndItemsData;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.monitor.changtian.bean.SampleInfoCompreData;
import com.monitor.changtian.bean.SampleInfoDataBean;
import com.monitor.changtian.bean.SampleStatusBean;
import com.monitor.changtian.bean.SampleType;
import com.monitor.changtian.bean.SampleTypeBean;
import com.monitor.changtian.bean.SampleWaterDataBean;
import com.monitor.changtian.bean.SamplingAssistDataBean;
import com.monitor.changtian.bean.SchemeDeviceBean;
import com.monitor.changtian.bean.SchemeFidsBean;
import com.monitor.changtian.bean.StatisticsMoneyQueryBean;
import com.monitor.changtian.bean.StatisticsQueryBean;
import com.monitor.changtian.bean.TaskBean;
import com.monitor.changtian.bean.TaskCarsDataBean;
import com.monitor.changtian.bean.TaskEquipmentDataBean;
import com.monitor.changtian.bean.TaskInfoAllBean;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.bean.TestProjectCycleListBean;
import com.monitor.changtian.bean.UserInfo;
import com.monitor.changtian.bean.WeatherBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by ken on 2018/4/24.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface ApiService {


    @GET("weather/json.shtml")
    Observable<WeatherBean> GetWeatherBean(@Query("city") String city);


    @POST("WebService.asmx/GetSampleWaterData")
    Observable<List<SampleWaterDataBean>> GetSampleWaterData(@Query("data") String cit);

    @Multipart
    @POST("WebService.asmx/AddSampleWaterDetailImfo")
    Observable<ResultBean> AddSampleWaterDetailImfo(@PartMap Map<String, RequestBody> patams);

    //查询所有提交记录
    @POST("WebService.asmx/GetSampleDetailData")
    Observable<List<SampleDetailDataBean>> GetSampleDetailData(@Query("data") String cit);


    //查询气象信息
    @POST("WebService.asmx/GetSampleMeteorologyData")
    Observable<List<SampleWaterDataBean>> GetSampleMeteorologyData(@Query("data") String cit);

    //提交气象信息
    @Multipart
    @POST("WebService.asmx/AddSampleMeterDetailImfo")
    Observable<ResultBean> AddSampleMeterDetailImfo(@PartMap Map<String, RequestBody> patams);

    //查询气象采样明细信息
    @POST("WebService.asmx/GetSampleMeterDetailData")
    Observable<List<SampleDetailDataBean>> GetSampleMeterDetailData(@Query("data") String cit);

    //查询土壤信息
    @POST("WebService.asmx/GetSampleSoilData")
    Observable<List<SampleWaterDataBean>> GetSampleSoilData(@Query("data") String cit);

    //提交土壤信息
    @Multipart
    @POST("WebService.asmx/AddSampleSoilDetailImfo")
    Observable<ResultBean> AddSampleSoilDetailImfo(@PartMap Map<String, RequestBody> patams);

    //修改密码
    @POST("MobileService.asmx/ModifyUserPwd")
    Observable<ResultBean> ModifyUserPwd(@Query("data") String data);

    //获取消息信息
    @POST("MobileService.asmx/GetMsgList")
    Observable<List<MessageBean>> GetMsgList(@Query("data") String data);

    //获取任务列表
    @POST("WebService2.asmx/GetTasksInfo")
    Observable<List<TaskListBean>> GetTastList(@Query("data") String data);

    //获取人员经纬度
    @POST("MobileService.asmx/GetPosTask")
    Observable<List<TaskBean>> GetPosTask(@Query("data") String data);


    //用户登录、
    @POST("MobileService.asmx/UserLogin")
    Observable<ResultBean> UserLogin(@Query("data") String data);


    //查询土壤信息
    @POST("WebService.asmx/GetSampleSoilDetailData")
    Observable<List<SampleDetailDataBean>> GetSampleSoilDetailData(@Query("data") String cit);


    /**
     * 根据类型查询当前采样类型
     */
    @POST("WebService.asmx/GetDicData")
    Observable<List<SampleType>> GetDicData(@Query("data") String data);

    /**
     * 获取对应类型的项目信息
     */
    @POST("WebService.asmx/GetSampleInfoData")
    Observable<List<SampleInfoDataBean>> GetSampleInfoData(@Query("data") String data);

    /**
     * 获取对应类型的采样信息
     */
    @POST("WebService.asmx/GetSampleCategoryItemsData")
    Observable<List<SampleCategoryItemsDataBean>> GetSampleCategoryItemsData(@Query("data") String datal);

    /**
     * 提交采样结果
     */
    @Multipart
    @POST("WebService4.asmx/AddfieldsamplingInfo")
    Observable<ResultBean> AddSampleDetailsInfo(@PartMap Map<String, RequestBody> patams);

    /**
     * 获取审核详情
     */
    @POST("WebService.asmx/GetSampleInfoCompreData")
    Observable<SampleInfoCompreData> GetSampleInfoCompreData(@Query("data") String data);


    /**
     * 审核通过
     */
    @POST("WebService.asmx/AddSampleAudit")
    Observable<ResultBean> AddSampleAudit(@Query("data") String data);

    /**
     * -
     * 查询采样单次xiangq
     */
    @POST("WebService.asmx/GetSampleDetailsAndItemsData")
    Observable<List<SampleDetailsAndItemsData>> GetSampleDetailsAndItemsData(@Query("data") String data);

    /**
     * 根据用户获取用户信息
     */
    @POST("WebService.asmx/GetUserInfo")
    Observable<List<UserInfo>> GetUserInfo(@Query("data") String data);


    /**
     * 修改采样记录
     */
    @Multipart
    @POST("WebService.asmx/UpdateSampleDetailsInfo")
    Observable<ResultBean> UpdateSampleDetailsInfo(@PartMap Map<String, RequestBody> patams);


    /**
     * 获取所有用户信息
     */
    @POST("FrontEnd.asmx/GetUserInfo")
    Observable<List<AllUserInfo>> GetAllUserInfo(@Query("data") String data);


    /**
     * 提交审核
     */
    @POST("WebService.asmx/SubmitSampleAudit")
    Observable<ResultBean> SubmitSampleAudit(@Query("data") String data);


    /**
     * 查询任务车辆信息
     */
    @POST("WebService2.asmx/GetTaskCarsData")
    Observable<List<TaskCarsDataBean>> GetTaskCarsData(@Query("data") String data);

    /**
     * 查询任务设备信息
     */
    @POST("WebService2.asmx/GetTaskEquipmentData")
    Observable<List<TaskEquipmentDataBean>> GetTaskEquipmentData(@Query("data") String data);

    /**
     * 查询任务人员信息
     */
    @POST("WebService2.asmx/GetPersonsData")
    Observable<List<PersonsDataBean>> GetPersonsData(@Query("data") String data);


    /**
     * 查询检测方案信息
     */
    @POST("WebService.asmx/GetDetectionSchemeData")
    Observable<List<DetectionSchemeDataBean>> GetDetectionSchemeData(@Query("data") String data);


    /**
     * 增加信息
     */
    @POST("WebService.asmx/AddDicData")
    Observable<ResultBean> AddBaseData(@Query("data") String data);

    /**
     * 查询信息
     */
    @POST("WebService.asmx/GetDicData")
    Observable<List<BuyWayBean>> GetBaseData(@Query("data") String data);

    /**
     * 删除信息
     */
    @POST("WebService.asmx/DeleteDicData")
    Observable<ResultBean> DeleteDicData(@Query("data") String data);

    /**
     * 查询财务信息
     */
    @POST("WebService.asmx/GetProjectData")
    Observable<List<FinanceListBean>> GetProjectData(@Query("data") String data);

    /**
     * 确认打款
     */
    @Multipart
    @POST("WebService2.asmx/AddMoneyRecord")
    Observable<ResultBean> AddMoneyRecord(@PartMap Map<String, RequestBody> patams);


    /**
     * 查询合同
     */
    @POST("WebService2.asmx/Getprojectcontract")
    Observable<List<ProjectcontractBean>> Getprojectcontract(@Query("data") String data);


    /**
     * 查询合同总款
     *
     * @return
     */
    @GET("WebService2.asmx/StatisticsQuery1")
    Observable<StatisticsQueryBean> StatisticsQuery1();

    /**
     * 查询金额统计
     */
    @GET("WebService2.asmx/StatisticsQuery")
    Observable<List<StatisticsMoneyQueryBean>> StatisticsQuery(@Query("data") String data);


    /**
     * 查询
     */
    @POST("WebService2.asmx/GetMoneyRecord")
    Observable<List<MoneyRecordBean>> GetMoneyRecord(@Query("data") String data);


    /**
     * 根据合同ID查询点位信息
     */
    @POST("WebService2.asmx/GetNoPointsTaskData")
    Observable<List<NoPointsTaskDataBean>> GetNoPointsTaskData(@Query("data") String data);

    /**
     * 查询采样人员与辅助人员
     */
    @POST("WebService2.asmx/GetSamplingAssistData")
    Observable<SamplingAssistDataBean> GetSamplingAssistData(@Query("data") String data);

    /**
     * 查询实验人员信息；
     */
    @POST("WebService2.asmx/GetFactorsExperimenterData")
    Observable<List<FactorsExperimenterDataBean>> GetFactorsExperimenterData(@Query("data") String data);

    /**
     * 查询车辆信息、
     */
    @POST("WebService.asmx/CarDataList")
    Observable<List<CarDataListBean>> GetCarDataList(@Query("data") String data);

    /**
     * 查询设备信息
     */
    @POST("WebService2.asmx/GetSchemeDevice1")
    Observable<List<SchemeDeviceBean>> GetSchemeDevice(@Query("data") String data);

    /**
     * 查询耗材信息
     */
    @POST("WebService2.asmx/GetSchemeConsumables1")
    Observable<List<SchemeConsumablesBean>> GetSchemeConsumables(@Query("data") String data);

    /**
     * 查询分配任务列表
     */
    @POST("WebService2.asmx/GetTasksInfo")
    Observable<List<TasksListInfoBean>> GetTasksInfo(@Query("data") String data);

    /**
     * 查询任务详情
     */

    @POST("WebService2.asmx/GetTasksInfoDetail")
    Observable<List<TasksInfoDetailBean>> GetTasksInfoDetail(@Query("data") String data);

    /**
     * 提交任务信息
     */
    @Multipart
    @POST("WebService2.asmx/AddTasksInfo")
    Observable<ResultBean> AddTasksInfo(@PartMap Map<String, RequestBody> patams);

    /**
     * 查询设备类型
     */
    @POST("WebService.asmx/GetDicData")
    Observable<List<DicDataBean>> GetDevData(@Query("data") String data);

    /**
     * 查询分类设备信息
     */
    @POST("WebService.asmx/GetEquipmentData")
    Observable<List<EquipmentDataBean>> GetEquipmentData(@Query("data") String data);

    /**
     * 查询设备信息
     */
    @POST("WebService.asmx/GetEquipmentData")
    Observable<List<EquipStateBean>> GetEquipmentState(@Query("data") String data);


    /**
     * 查询设备类型
     */
    @POST("WebService.asmx/GetEquipmentData")
    Observable<EquipTypeBean> GetEquipmentType(@Query("data") String data);

    /**
     * 查询耗材分类信息
     */
    @POST("WebService2.asmx/GetConsumablesData")
    Observable<List<EquipmentDataBean>> GetConsumablesData(@Query("data") String data);

    /**
     * 查询任务详细
     */
    @POST("WebService4.asmx/GetTasksInfomation")
    Observable<List<TasksInfomationBean>> GetTasksInfomation(@Query("data") String data);

    /**
     * 添加采样信息
     */
    @Multipart
    @POST("WebService4.asmx/AddfieldsamplingInfo")
    Observable<ResultBean> AddfieldsamplingInfo(@PartMap Map<String, RequestBody> patams);

    /**
     * +
     * 查询采样记录列表
     */
    @POST("WebService4.asmx/GetfieldsamplingInfo")
    Observable<List<FieldsamplingInfo>> GetfieldsamplingInfo(@Query("data") String data);

    /**
     * 结束任务
     */
    @POST("WebService4.asmx/EndfieldsamplingInfo")
    Observable<ResultBean> EndfieldsamplingInfo(@Query("data") String data);


    /**
     * 查询采样批次记录
     */
    @POST("WebService4.asmx/GetfieldsamplingDetailList")
    Observable<List<FieldsamplingDetailListBean>> GetfieldsamplingDetailList(@Query("data") String data);


    /**
     * 查询详细采样记录信息
     */
    @POST("WebService4.asmx/GetfieldsamplingDetail")
    Observable<List<FieldsamplingDetailBean>> GetfieldsamplingDetail(@Query("data") String data);

    /**
     * 查询方案信息
     */
    @POST("WebService.asmx/GetDetectionSchemeInfo")
    Observable<List<DetectionSchemeInfoBean>> GetDetectionSchemeInfo(@Query("data") String data);

    /**
     * 查询待审核方案
     */

    @POST("WebService.asmx/GetDetectionScheme")
    Observable<List<DetectionSchemeBean>> GetDetectionScheme(@Query("data") String data);

    /**
     * 方案审核
     */
    @POST("WebService.asmx/AddDetectionSchemeAudit")
    Observable<ResultBean> AddDetectionSchemeAudit(@Query("data") String data);

    @POST("WebService2.asmx/GetSchemeQuotationInfo")
    Observable<List<GetSchemeQuotationInfoBean>> GetSchemeQuotationInfo(@Query("data") String data);

    @POST("WebService2.asmx/GetSchemePriceDetailList")
    Observable<List<GetSchemePriceDetailListBean>> GetSchemePriceDetailList(@Query("data") String data);

    @POST("WebService4.asmx/AddSchemePriceAudit1")
    Observable<ResultBean> GetOffer(@Query("data") String data);

    @POST("WebService4.asmx/AddSchemePriceAudit2")
    Observable<ResultBean> GetOffered(@Query("data") String data);

    /**
     * 质控
     */
    @POST("WebService4.asmx/Getqualitycontrol")
    Observable<List<GetqualitycontrolBean>> Getqualitycontrol(@Query("data") String data);

    @Multipart
    @POST("WebService4.asmx/Addqualitycontrol")
    Observable<ResultBean> Addqualitycontrol(@PartMap Map<String, RequestBody> patams);


    /**
     * 添加采样信息
     */
    @Multipart
    @POST("WebService2.asmx/AddTaskStop1")
    Observable<ResultBean> AddTaskStop1(@PartMap Map<String, RequestBody> patams);

    /**
     * 查询任务状态
     */
    @POST("WebService2.asmx/GetTasksInfoAudit")
    Observable<List<TaskInfoAllBean>> GetTasksInfoAudit(@Query("data") String data);

    /**
     * 审核暂停任务
     */
    @POST("WebService2.asmx/AddManagerTaskAudit")
    Observable<ResultBean> AddManagerTaskAudit(@Query("data") String data);

    @POST("WebService4.asmx/AddsampleInformation")
    Observable<ResultBean> AddsampleInformation(@Query("data") String data);

    @POST("WebService4.asmx/GetsampleInformation")
    Observable<List<Task_SapmleBean>> GetsampleInformation(@Query("data") String data);

    @POST("WebService4.asmx/GetsamplemanageInfo")
    Observable<List<GetsamplemanageInfoBean>> GetsamplemanageInfo(@Query("data") String data);

    @POST("WebService4.asmx/Addsamplemanage")
    Observable<ResultBean> Addsamplemanage(@Query("data") String data);

    @POST("WebService4.asmx/GetfieldsamplingDetailList")
    Observable<List<GetfieldsamplingDetailListBean>> fieldsamplingDetailList(@Query("data") String data);

    @POST("WebService4.asmx/proofreadingsamplingdetail")
    Observable<ResultBean> proofreadingsamplingdetail(@Query("data") String data);

    @POST("WebService4.asmx/auditsamplingdetail")
    Observable<ResultBean> auditsamplingdetail(@Query("data") String data);

    /**
     * 终止renwu
     */
    @POST("WebService2.asmx/AddTaskEnd")
    Observable<ResultBean> AddTaskEnd(@Query("data") String data);

    /**
     * 设备领用
     */
    @POST("WebService.asmx/AddEquipOutStock")
    Observable<ResultBean> AddEquipOutStock(@Query("data") String data);
    /**
     * 查询校准设备列表
     */
    @POST("WebService/WebService.asmx/GetCsoundDev")
    Observable<List<EquipmentDataBean>> GetCalibrateDev(@Query("data") String data);

    /**
     * 保存噪声校准记录
     */
    @POST("WebService5.asmx/CalibrateSave")
    Observable<ResultBean> CalibrateSave(@Query("data") String data);

    /**
     * 保存噪环境空气 7040的校准记录
     */
    @POST("WebService5.asmx/Gas7040Save")
    Observable<ResultBean> Gas7040Save(@Query("data") String data);

    /**
     * 查询设备
     */
    @POST("WebService.asmx/GetEquipInOutStockData")
    Observable<List<EquipInOutStockDataBean>> GetEquipInOutStockData(@Query("data") String data);

    /**
     * 耗材领用
     */
    @POST("WebService2.asmx/AddConsumablesOutStock")
    Observable<ResultBean> AddConsumablesOutStock(@Query("data") String data);

    /**
     * 查询相关耗材
     *
     * @param data
     * @return
     */
    @POST("WebService2.asmx/GetConsumablesDataByTaskid")
    Observable<List<GetConsumablesDataBean>> GetConsumablesDataByTaskid(@Query("data") String data);

    /***
     * 查询领用耗材
     */
    @POST("WebService2.asmx/GetConsumablesInOutStockOptionData")
    Observable<List<EquipInOutStockDataBean>> GetConsumablesInOutStockOptionData(@Query("data") String data);


    /**
     * 设备归还
     */
    @POST("WebService.asmx/AddEquipInStock")
    Observable<ResultBean> AddEquipInStock(@Query("data") String data);

    /**
     * 耗材归还
     */
    @POST("WebService2.asmx/AddConsumablesInStock")
    Observable<ResultBean> AddConsumablesInStock(@Query("data") String data);


    /**
     * 查询样品室
     */
    @POST("WebService2.asmx/GetRoomList")
    Observable<List<RoomListBean>> GetRoomList(@Query("data") String data);

    /**
     * 根据编号查询样品状态
     */
    @POST("WebService4.asmx/GetsampleInfoByonlynumber")
    Observable<SampleStatusBean> GetsampleInfoByonlynumber(@Query("data") String data);

    /**
     * 拒收样品
     */
    @POST("WebService4.asmx/Addsamplerejection")
    Observable<ResultBean> Addsamplerejection(@Query("data") String data);


    /**
     * 根据用户权限查询消息列表
     */
    @POST("MobileService.asmx/GetMsgList1")
    Observable<List<MessageListBean>> GetMsgLists(@Query("data") String data);


    /**
     * 添加附件
     */
    @Multipart
    @POST("WebService.asmx/AddfujianInfo")
    Observable<ResultBean> AddfujianInfo(@PartMap Map<String, RequestBody> patams);


    @POST("WebService4.asmx/GetsampleInfoBysamplecode")
    Observable<SampleTypeBean> GetsampleInfoBysamplecode(@Query("data") String data);

    /**
     * 根据加密样品返回解密
     */
    @POST("WebService4.asmx/Getsamplecode")
    Observable<ResultBean> Getsamplecode(@Query("data") String data);

    /**
     * 查询因子分组情况
     * WebService5.asmx/GetSampleInfoByPointInfo
     */

    @POST("WebService5.asmx/GetSampleInfoByPointInfo")
    Observable<SampleInfoByPointInfoBean> GetSampleInfoByPointInfo(@Query("data") String data);


    /**
     * 验证用户是否失效
     */
    @POST("MobileService.asmx/VerificationTockenInfo")
    Observable<ResultBean> VerificationTockenInfo(@Query("data") String data);


    /**
     * 查询标准物质列表
     */
    @POST("TestService.asmx/GetPreData")
    Observable<PreDataBean> GetPreData(@Query("data") String data);

    /**
     * 查询标准物质因子列表
     */
    @POST("TestService.asmx/GetPreData4")
    Observable<List<PreInfoDataBean>> GetPreData4(@Query("data") String data);

    /**
     * 查询全程序空白样因子列表
     */
    @POST("WebService2.asmx/GetSchemeFids")
    Observable<List<SchemeFidsBean>> GetSchemeFids(@Query("data") String data);


    /**
     * 领样
     */
    @POST("TestService.asmx/GetSampleInfoByAssignmentRecord")
    Observable<SampleInfoByAssignmentRecordBean> GetSampleInfoByAssignmentRecord(@Query("data") String data);


    /**
     * 提交领养
     */
    @POST("TestService.asmx/CollarSample")
    Observable<ResultBean> CollarSample(@Query("data") String data);


    /**
     * 质控审核  获取列表数据信息
     */
    @POST("TestService.asmx/GetTestProjectCycleList")
    Observable<List<TestProjectCycleListBean>> GetTestProjectCycleList(@Query("data") String data);

    /**
     * 质控审核列表
     */
    @POST("TestService.asmx/GetprojectTestList")
    Observable<List<ProjectTestListBean>> GetprojectTestList(@Query("data") String data);

    /**
     * 查询质控审核详情
     */
    @POST("TestService.asmx/QueryTestRecord")
    Observable<List<QueryTestRecordBean>> QueryTestRecord(@Query("data") String data);


    /**
     * 提交质控审核信息、
     */
    @Multipart
    @POST("TestService.asmx/qcApproveprojectTest")
    Observable<ResultBean> qcApproveprojectTest(@PartMap Map<String, RequestBody> patams);


}

