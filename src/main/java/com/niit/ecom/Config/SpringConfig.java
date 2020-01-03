
  
package com.niit.ecom.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.ecom.dao.CartDAOImpl;
import com.niit.ecom.dao.CartDAO;

import com.niit.ecom.dao.CategoryDAO;
import com.niit.ecom.dao.CategoryDAOImpl;
import com.niit.ecom.dao.OrderDAOImpl;
import com.niit.ecom.dao.ProductDAO;
import com.niit.ecom.dao.ProductDAOImpl;
import com.niit.ecom.dao.SupplierDAO;
import com.niit.ecom.dao.SupplierDAOImpl;
import com.niit.ecom.dao.UserDetailDAO;
import com.niit.ecom.dao.UserDetailDAOImpl;
import com.niit.ecom.model.Cart;
import com.niit.ecom.model.Category;
import com.niit.ecom.model.Product;
import com.niit.ecom.model.Supplier;
import com.niit.ecom.model.UserDetail;



@Configuration
@EnableTransactionManagement
public class DBConfig {

private static final String name = null;

@Bean(name="DataSource")
	public DataSource getH2DataSource() {
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		
		datasource.setDriverClassName("org.h2.Driver");
		datasource.setUrl("jdbc:h2:tcp://localhost/~/test");
		datasource.setUsername("sa");
		datasource.setPassword("");
    	System.out.println("Data source object created");
		return datasource;
	}
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory() {
		Properties hibernateProp=new Properties();
		
		
		hibernateProp.put("hibernate.hbm2ddl.auto","update");
		hibernateProp.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		hibernateProp.put("hibernate.show_sql","true");
	
		LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(getH2DataSource());
		factory.addProperties(hibernateProp);
		factory.addAnnotatedClass(Category.class);
		factory.addAnnotatedClass(Supplier.class);
		factory.addAnnotatedClass(Product.class);
		factory.addAnnotatedClass(UserDetail.class);
		factory.addAnnotatedClass(Cart.class);
		
		SessionFactory sessionFactory = factory.buildSessionFactory();
		System.out.println("SessionFactory created");
		return sessionFactory;
	}
	@Bean(name="categoryDAO")
    public CategoryDAOImpl getCategoryDAO() {
	System.out.println("--- category bean created");
       return new CategoryDAOImpl();
   
    }


       
       @Bean(name="supplierDAO")
       public SupplierDAOImpl getSupplierDAO() {
    	   System.out.println("supplier bean created");
    	   return new SupplierDAOImpl();
    	   
       }
      
       @Bean(name="productDAO")
       public ProductDAOImpl getProductAO() {
    	   System.out.println("product bean created");
    	   return new ProductDAOImpl();
    	   
       }
@Bean(name="userDetailDAO")
public UserDetailDAOImpl getUserDetailDAO() {
	System.out.println("userdetailbean created");
	return new UserDetailDAOImpl();
	
}
	@Bean(name="cartDAO")
	public CartDAOImpl getCartDAO() {
		System.out.println("cartbean created ");
		return new CartDAOImpl();
	}
	
	@Bean(name="orderDAO")
	public OrderDAOImpl getOrderDAO() {
		System.out.println("orderbean created");
		return new OrderDAOImpl();
		
	}

@Bean(name="txManager")
public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	System.out.println("---hibernate category bean created");
       return new HibernateTransactionManager(sessionFactory);
}
}
