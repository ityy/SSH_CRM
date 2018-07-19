package cn.yang.web.action;

import cn.yang.domain.LinkMan;
import cn.yang.service.LinkManService;
import cn.yang.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();
    private LinkManService linkManService;
    //属性驱动 封装数据
    private Integer currentPage;
    private Integer pageSize;


    public String add() throws Exception {
        if (linkMan.getLkm_name() != null) {
            linkManService.saveOrUpdate(linkMan);
        }
        return "toList";

    }

    public String list() throws Exception {
        System.out.println("Action:LinkManAction:list正在执行");

        // 创建离线查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);

        /*
            如果有筛选，则添加筛选条件，否则不添加
            isNotBlank是否非空，此方法排除空格参数
            两个筛选条件，可一个有效，可同时有效
        */
        if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
            detachedCriteria.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
        }
        if (linkMan.getCustomer() != null && linkMan.getCustomer().getCust_id() != null) {
            detachedCriteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));

        }

        //1 调用service查询分页信息放到PageBean

        PageBean pageBean = linkManService.getPageBean(detachedCriteria, currentPage, pageSize);
        //2 将PageBeanUtils放入request域
        ActionContext.getContext().put("pageBean", pageBean);
        //3 转发到列表页面显示

        return "list";


    }
    public String toEdit() throws Exception {
        //根据lkm_id获得LinkMan
        LinkMan lkm = linkManService.getById(linkMan.getLkm_id());
        //放入值域
        ActionContext.getContext().put("linkMan", lkm);
        //重定向到修改页
        return "toEdit";
    }

    /*
        --------------------  框架赋值_分割线  -----------------------------
     */


    @Override
    public LinkMan getModel() {
        return linkMan;
    }


    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
