package cn.yang.web.action;

import cn.yang.domain.SaleVisit;
import cn.yang.domain.User;
import cn.yang.service.SaleVisitService;
import cn.yang.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
    private SaleVisit saleVisit = new SaleVisit();
    private SaleVisitService saleVisitService;

    public String add() throws Exception {
        System.out.println("SaleVisitAction:add--正在执行");

        //1 将登陆用户放入saleVisit 表达关系
        saleVisit.setUser((User) ActionContext.getContext().getSession().get("user"));
        //2 保存拜访记录
        saleVisitService.save(saleVisit);
        //3 重定向到列表
        return "toList";
    }

    private Integer currentPage;
    private Integer pageSize;

    public String list()throws Exception {
        System.out.println("SaleVisitAction:list--正在执行");
        //1 封装离线查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
        //2 根据是否有筛选条件 封装查询条件
        if (saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null) {
            detachedCriteria.add(Restrictions.eq("customer_cust_id", saleVisit.getCustomer().getCust_id()));
        }

        //3 查询分页数据
        PageBean pageBean = saleVisitService.getPageBean(detachedCriteria, currentPage, pageSize);
        //4 将数据放入request域并转发到页面显示
        ActionContext.getContext().put("pageBean", pageBean);
        return "list";
    }

    public String toEdit()throws Exception {
        //1 根据id查询对象
        SaleVisit sv = saleVisitService.getById(saleVisit.getVisit_id());
        //2 放入request域并转发
        ActionContext.getContext().put("saleVisit", sv);
        return "add";
    }


    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }

    public SaleVisit getSaleVisit() {
        return saleVisit;
    }

    public void setSaleVisit(SaleVisit saleVisit) {
        this.saleVisit = saleVisit;
    }

    public SaleVisitService getSaleVisitService() {
        return saleVisitService;
    }

    public void setSaleVisitService(SaleVisitService saleVisitService) {
        this.saleVisitService = saleVisitService;
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
