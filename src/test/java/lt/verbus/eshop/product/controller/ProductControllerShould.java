package lt.verbus.eshop.product.controller;

import lt.verbus.eshop.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerShould {

    @MockBean
    private DataSource dataSource;

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mvc;


    @Test
    void return_products_page_with_no_products_when_no_products_found() throws Exception {
        // given
        when(productService.getPageOfProducts(PageRequest.of(0, 5))).thenReturn(Page.empty());

        // when
        ResultActions aaa = mvc.perform(get("/public/product"))
                .andExpect(status().isOk())
                .andExpect((view().name("product/product-list")))
                .andExpect(content().string(containsString("<p>Produktų nėra</p>")));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void not_allow_to_create_new_product_for_user_role() throws Exception {
        // when
        mvc.perform(get("/public/product/new"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(roles = {"ADMIN"})
    void allow_to_create_new_product_for_admin_role() throws Exception {
        // when
        mvc.perform(get("/public/product/new"))
                .andExpect(status().isOk());
    }

}