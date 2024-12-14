package com.vag.product.resources;

import com.vag.product.ProductPrototype;
import com.vag.product.dto.ProductDto;
import com.vag.product.repository.ProductRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

// DIFF: We can use our tests like a bean and inject our dependencies via constructor
@QuarkusTest
@RequiredArgsConstructor
class ProductResourceTest {

    private final ProductRepository productRepository;

    @BeforeEach
    @Transactional
    void beforeEach() {
        productRepository.deleteAll();
    }

    @Test
    void shouldSaveANewProduct() {
        // when
        var googlePixel = createProduct(ProductPrototype.GOOGLE_PIXEL_PRODUCT);

        // then
        assertThat(googlePixel)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("brand", ProductPrototype.GOOGLE_PIXEL_PRODUCT.brand())
                .hasFieldOrPropertyWithValue("name", ProductPrototype.GOOGLE_PIXEL_PRODUCT.name())
                .hasFieldOrPropertyWithValue("unitPrice", ProductPrototype.GOOGLE_PIXEL_PRODUCT.unitPrice());
    }

    @Test
    void shouldUpdateANewProduct() {
        // given
        var googlePixel = createProduct(ProductPrototype.GOOGLE_PIXEL_PRODUCT);
        var googlePixelToBeUpdated = googlePixel.toBuilder()
                .brand("Google Corp")
                .unitPrice(BigDecimal.valueOf(RandomUtils.insecure().randomLong(5000, 5300)))
                .build();

        // when
        var googlePixelUpdated = given().when()
                .contentType(ContentType.JSON)
                .body(googlePixelToBeUpdated)
                .put("/api/product/{id}", googlePixel.id())
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(ProductDto.class);

        // then
        assertThat(googlePixelUpdated)
                .hasFieldOrPropertyWithValue("id", googlePixelUpdated.id())
                .hasFieldOrPropertyWithValue("brand", googlePixelUpdated.brand())
                .hasFieldOrPropertyWithValue("name", googlePixelUpdated.name())
                .hasFieldOrPropertyWithValue("unitPrice", googlePixelUpdated.unitPrice());
    }

    @Test
    void shouldGetOneSingleProduct() {
        // given
        var iphone = createProduct(ProductPrototype.IPHONE_PRODUCT);

        // when
        ProductDto productDtoUpdated = given().when()
                .get("/api/product/{id}", iphone.id())
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(ProductDto.class);

        // then
        assertThat(productDtoUpdated)
                .hasFieldOrPropertyWithValue("id", iphone.id())
                .hasFieldOrPropertyWithValue("brand", iphone.brand())
                .hasFieldOrPropertyWithValue("name", iphone.name())
                .hasFieldOrPropertyWithValue("unitPrice", iphone.unitPrice());
    }

    @Test
    void shouldGetListOfProducts() {
        // given
        var iphone = createProduct(ProductPrototype.IPHONE_PRODUCT);
        var googlePixel = createProduct(ProductPrototype.GOOGLE_PIXEL_PRODUCT);

        // when
        List<ProductDto> productsDto = given().when()
                .get("/api/product")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(new TypeRef<List<ProductDto>>() {
                });

        // then
        assertThat(productsDto)
                .hasSize(2)
                .containsExactlyInAnyOrder(iphone, googlePixel);
    }

    private ProductDto createProduct(ProductDto productDto) {
        return given().when()
                .contentType(ContentType.JSON)
                .body(productDto)
                .post("/api/product")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract()
                .as(ProductDto.class);
    }
}