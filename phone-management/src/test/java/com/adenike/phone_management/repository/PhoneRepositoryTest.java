package com.adenike.phone_management.repository;

import com.adenike.phone_management.model.Phone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase// For testing JPA repositories with an in-memory database
public class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    @Order(1)
    @Rollback(false)
    public void PhoneRepository_SaveAll_ReturnSavedPhone() {

        //Arrange/Given
        Phone phone = Phone.builder()
                .model("iPhone 13")
                .brand("Apple")
                .price(200000.00)
                .build();

        //Act
        Phone savedPhone = phoneRepository.save(phone);

        //Assert
        Assertions.assertThat(savedPhone).isNotNull();
        Assertions.assertThat(savedPhone.getId()).isGreaterThan(0);//Ensure Id is generated
        Assertions.assertThat(savedPhone.getModel()).isEqualTo("iPhone 13");
        Assertions.assertThat(savedPhone.getBrand()).isEqualTo("Apple");
        Assertions.assertThat(savedPhone.getPrice()).isEqualTo(200000.00);
    }

    @Test
    @Order(2)
    @Rollback(false)
    public void PhoneRepository_GetByIdPhone_ReturnSavedPhone() {
        // Arrange
        Phone phone = phoneRepository.findById(1L).get();

        // Act and Assert
        Assertions.assertThat(phone).isNotNull();
        Assertions.assertThat(phone.getId()).isEqualTo(1L);
    }


    @Test
    @Order(3)
    @Rollback(false)
    public void PhoneRepository_FindByIdPhone() {
        List<Phone> phoneList = phoneRepository.findAll();

        Assertions.assertThat(phoneList).isNotNull();
        //Assertions.assertThat(phone.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(false)
    public void PhoneRepository_UpdatePhone_UpdatedPhone() {
        //Arrange
        Phone phone = phoneRepository.findById(1L).get();

        phone.setModel("iPhone 13");
        phone.setBrand("Apple");
        phone.setPrice(400000.00);

        Phone phoneUpdated = phoneRepository.save(phone);
        Assertions.assertThat(phoneUpdated.getPrice()).isEqualTo(400000.00);

    }

    @Test
    @Order(5)
    @Rollback(false)
    public void PhoneRepository_DeletePhone_DeletedPhone() {
        // Arrange: Retrieve the phone object to delete
        Phone phone = phoneRepository.findById(1L).get();
        Assertions.assertThat(phone).isNotNull(); // Ensure the phone exists before deletion

        // Act: Delete the phone
        phoneRepository.delete(phone);

        // Assert: Verify the phone is no longer in the database
        Optional<Phone> deletedPhone = phoneRepository.findById(1L);
        Assertions.assertThat(deletedPhone).isEmpty(); // The phone should no longer exist
    }

}