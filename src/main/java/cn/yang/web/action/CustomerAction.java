package cn.yang.web.action;

import cn.yang.domain.Customer;
import cn.yang.service.CustomerService;
import cn.yang.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    //模型驱动 封装数据,Customer中的3个外键对象，如果也有属性过来，struts2会自动创建外键对象并赋值。
    //这里传递来的外键属性是dict_id，这样这3个外键就都有id了，成为游离状态的对象。可以直接save Customer而不用手动维护外键关系了。
    private Customer customer = new Customer();
    //属性驱动 封装数据
    private Integer currentPage;
    private Integer pageSize;
    //等待spring注入
    private CustomerService customerService;

    //在后台提供一个与前台input type=file组件 name相同的属性
    private File photo;
    //在提交键名后加上固定后缀FileName,文件名称会自动封装到属性中 (含后缀名)
    private String photoFileName;
    //在提交键名后加上固定后缀ContentType,文件MIME类型会自动封装到属性中
    private String photoContentType;

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }


    public String list() throws Exception {
        System.out.println("CustomerAction:list正在执行");

        // 封装离线查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);

        /*
            如果有筛选，则添加筛选条件，否则不添加
            isNotBlank是否非空，此方法排除空格参数
        */
        if (StringUtils.isNotBlank(customer.getCust_name())) {
            detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }

        //1 调用service查询分页信息放到PageBean
        PageBean pageBean = customerService.getPageBean(detachedCriteria, currentPage, pageSize);
        //2 将PageBeanUtils放入request域
        ActionContext.getContext().put("pageBean", pageBean);
        //3 转发到列表页面显示

        return "list";


    }

    public String add() throws Exception {
        System.out.println("CustomerAction:add--正在执行");

        if (photo != null) {
            //题外：文件上传  保存图片
            photo.renameTo(new File("D:/" + photoFileName));
        }
        //1 调用service存储
        customerService.saveOrUpdate(customer);
        //2 重定向到列表页
        return "toList";

    }


    public String toEdit() throws Exception {
        System.out.println("CustomerAction:toEdit--正在执行");
        //根据cust_id获得customer
        Customer cs = customerService.getById(customer.getCust_id());
        //放入值域
        ActionContext.getContext().put("customer", cs);
        //重定向到修改页
        return "toEdit";
    }

    public String industryCount() throws Exception {

        List<Object[]> list = customerService.getIndustryCount();

        ActionContext.getContext().put("list", list);

        return "industryCount";

    }


    @Override
    public Customer getModel() {
        return customer;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
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
