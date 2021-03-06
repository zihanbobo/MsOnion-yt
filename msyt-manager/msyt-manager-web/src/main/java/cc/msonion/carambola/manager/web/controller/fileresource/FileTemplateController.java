/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */


package cc.msonion.carambola.manager.web.controller.fileresource;

/**
 * @Title: FileTemplateController.java
 * @Package: cc.msonion.carambola.manager.web.controller.fileresource
 * @Description: 文件模板controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年07月19日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年07月19日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.fileresource.interfaces.FileResourceTemplateService;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplate;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplateExample;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionSysSetCodeConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: FileTemplateController
 * @Description: 文件模板controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年07月19日
 *
 */
@Controller
public class FileTemplateController extends MsOnionBaseAppController {

    /**
     * sysDataDictionaryService
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * sysDataDictionaryService
     */
    @Autowired
    private FileResourceTemplateService fileResourceTemplateService;

    /**
     * memberService
     */
    @Autowired
    private MemberService memberService;


    /**
     * 进入数据字典列表页面
     *
     * @param req   请求对象
     * @param res   响应对象
     * @param model 页面
     * @return 页面
     */
    @RequestMapping("/fileTemplate/list")
    public String toList(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
            model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
            model.addAttribute("statusMap", statusMap);
            Map categoryMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.TEMPLATE_CATEGORY);
            model.addAttribute("categoryMap", categoryMap);
            model.addAttribute("categoryMapJson", MsOnionJsonUtils.objectToJson(categoryMap));


            Map appPlatformMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.APP_PLATFORM);
            model.addAttribute("appPlatformMapJson", MsOnionJsonUtils.objectToJson(appPlatformMap));

        } catch (MsOnionException e) {
            this.getMsOnionLogger().info(getClass().getName(), e, "### 查询系统设置失败。");
            return getError();
        }
        return "/fileresource/template/templateList";
    }


    /**
     * 新增、编辑、查询页面
     *
     * @param req   HttpServletRequest 对象
     * @param res   HttpServletResponse  对象
     * @param type  操作类型：add，edit，view (新增时idx =1 )
     * @param idx   菜单主键idx
     * @param model Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/fileTemplate/{type}/{idx}")
    public String toSaveOrEditOrViewMenu(HttpServletRequest req, HttpServletResponse res,
                                         @PathVariable String type, @PathVariable String idx, Model model) {
        try {
            if (!MsOnionRegexUtils.checkDigit(idx)) {
                this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idx=" + idx);
                return getError();
            }


            String templateSize = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_TEMPLATE_SIZE);
            String templateSuffix = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_TEMPLATE_SUFFIX);
            model.addAttribute("templateSize", templateSize);
            model.addAttribute("templateSuffix", templateSuffix);


            Map categoryMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.TEMPLATE_CATEGORY);
            model.addAttribute("categoryMap", categoryMap);

            Map appPlatformMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.APP_PLATFORM);
            model.addAttribute("appPlatformMap", appPlatformMap);


            if (ManagerConstants.ADD.equals(type)) {
                model.addAttribute("type", type);

            } else if (ManagerConstants.EDIT.equals(type) || ManagerConstants.VIEW.equals(type)) {
                MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
                FileResourceTemplate fileResourceTemplate = fileResourceTemplateService.queryByPrimaryKey(msOnionApiVersion, Long.valueOf(idx));
                model.addAttribute("template", fileResourceTemplate);
                model.addAttribute("type", type);
            } else {
                return getError();
            }
        } catch (MsOnionException e) {
            this.getMsOnionLogger().info(getClass().getName(), e, "### 进入系统设置操作页失败。");
            return getError();
        }
        return "/fileresource/template/saveOrEditOrViewTemplate";
    }

    /**
     * 根据主键 修改状态
     *
     * @param req       HttpServletRequest实例对象
     * @param res       HttpServletResponse实例对象
     * @param idxStr    主键idx
     * @param statusStr 状态
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/fileTemplate/updateStatus/{idxStr}/{statusStr}", method = {RequestMethod.POST})
    @ResponseBody
    public MsOnionResult updateStatus(HttpServletRequest req, HttpServletResponse res,
                                      @PathVariable String idxStr, @PathVariable String statusStr) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        long idx = 0;
        short status = 0;
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
            }
            if (!MsOnionRegexUtils.isNumeric(statusStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", statusStr=" + statusStr);
            }
            idx = Long.parseLong(idxStr);
            status = Short.parseShort(statusStr);

            int result = fileResourceTemplateService.updateStatus(msOnionApiVersion, idx, status);
            if (result > 0) {
                return MsOnionResult.ok();
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_SERVER_ERROR);

        } catch (MsOnionIllegalArgumentException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     * 查询分页列表 （easyui-datagird）
     *
     * @param req  HttpServletRequest实例对象
     * @param res  HttpServletResponse实例对象
     * @param page 页码
     * @param rows 每页数量
     * @return 返回MsOnionResult
     */
    @RequestMapping("/fileTemplate/paging")
    @ResponseBody
    public Map<String, Object> paging(HttpServletRequest req, HttpServletResponse res, String page, String rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果是非法参数， 可以默认第1页，10条记录
        if (!MsOnionRegexUtils.checkDigit(page)) {
            page = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE + "";
        }
        if (!MsOnionRegexUtils.checkDigit(rows)) {
            rows = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE + "";
        }
        try {
            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);

            String categoryIdx = req.getParameter("categoryIdx");
            String status = req.getParameter("status");


            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            FileResourceTemplateExample example = new FileResourceTemplateExample();
            String  orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;

            String sort = req.getParameter("sort");
            String order = req.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + " " + order.trim();
            }

            FileResourceTemplateExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(categoryIdx) && MsOnionRegexUtils.isNumeric(categoryIdx)) {
                criteria.andCategoryIdEqualTo(Short.valueOf(categoryIdx));
            }
            if (StringUtils.isNotBlank(status) && MsOnionRegexUtils.isNumeric(status)) {
                criteria.andStatusEqualTo(Short.valueOf(status));
            } else {
                criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            }

            MsOnionResultAdapter msOnionResultAdapter = fileResourceTemplateService.queryListByPageForResult(msOnionApiVersion, example,
                    pageNum, pageSize, orderBy);

            if (msOnionResultAdapter != null) {
                MsOnionPagingResult msOnionPagingResult = (MsOnionPagingResult) msOnionResultAdapter;
                map.put("total", msOnionPagingResult.getTotalCounts());
                map.put("rows", msOnionPagingResult.getData());
                List<FileResourceTemplate> list = (List<FileResourceTemplate>) msOnionPagingResult.getData();
                list.stream().forEach(s -> {
                    try {
                        Member member = memberService.queryByPrimaryKey(msOnionApiVersion, s.getUpdateByMemberIdx());
                        s.setUpdateByMemberIdxDynamicS(member.getName());
                        // 19位Long转成js有问题，需要转成String
                        s.setFileMessageIdDynamicS(String.valueOf(s.getFileMessageId()));
                    } catch (MsOnionException e) {
                        this.getMsOnionLogger().error(getClass().getName(), e);
                    }
                });
            }
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
        }
        return map;
    }

    /**
     * @param req   HttpServletRequest 对象
     * @param res   HttpServletResponse  对象
     * @param model Model实例对象
     * @param fileResourceTemplate  fileResourceTemplate 对象
     * @return MsOnionResult对象
     */
    @RequestMapping(value = "/fileTemplate/do-saveOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult doEdit(HttpServletRequest req, HttpServletResponse res, Model model, FileResourceTemplate fileResourceTemplate) {
        try {
            Member currentUser = CurrentUserUtils.getCurrentUser();
            fileResourceTemplate.setCreateByMemberIdx(currentUser.getIdx());
            fileResourceTemplate.setUpdateByMemberIdx(currentUser.getIdx());

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            if (Objects.isNull(fileResourceTemplate.getIdx())) {
                MsOnionResult msOnionResult = fileResourceTemplateService.saveFileTemplate(msOnionApiVersion, fileResourceTemplate);
                return msOnionResult;
            }
            MsOnionResult msOnionResult = fileResourceTemplateService.updateFileTemplate(msOnionApiVersion, fileResourceTemplate);
            return msOnionResult;
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionExecuteResultStatus.ERROR.getValue(), MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

}
