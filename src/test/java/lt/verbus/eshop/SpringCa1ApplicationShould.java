package lt.verbus.eshop;

import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.product.repository.ProductRepository;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//END TO END TESTING
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestEntityManager
class SpringCa1ApplicationShould {


//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private TestEntityManager em;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//                .build();
//    }
//
//
//    @Test
//    void create_product() throws Exception {
//        //  given
//
//        //  when
//        BasicNameValuePair namePair = new BasicNameValuePair("name", "Produkto Naujo Pavadinimas");
//        BasicNameValuePair inStockPair = new BasicNameValuePair("inStock", "10");
//        BasicNameValuePair pricePair = new BasicNameValuePair("price", "9.99");
//        BasicNameValuePair descriptionPair = new BasicNameValuePair("description", "Aprasymas");
//        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(List.of(namePair, inStockPair, pricePair, descriptionPair));
//        String formValues = EntityUtils.toString(urlEncodedFormEntity);
//
//        MvcResult mvcResult = mvc.perform(
//                post("/public/product/new")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .content(formValues))
//                .andExpect(status().is3xxRedirection())
//                .andReturn();
//
//        //  then
//        Long primaryKey = null;
//
//        Product productFromEm = em.find(Product.class, primaryKey);
//        Product productFromRepo = productRepository.getOne(primaryKey);
//
//        assertNotNull(productFromEm);
//        assertNotNull(productFromRepo);
//    }

}
