package nl.novi.finalAssignmentBackend.Service;

import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShoppingListServiceTest {

    @InjectMocks
    private ShoppingListService shoppingListService;

    @Mock
    private ShoppingListMapper shoppingListMapper;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @BeforeEach
    public void setUp(){
        shoppingListMapper = Mockito.mock();
        shoppingListRepository = Mockito.mock();
        shoppingListService = Mockito.mock();

    }

    @Test
    @DisplayName("get all movies")
    public void getAllShoppingListsMock(){


    }

}
