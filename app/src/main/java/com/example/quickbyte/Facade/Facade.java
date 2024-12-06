package com.example.quickbyte.Facade;

import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.DTO.BusinessOwnerDTO;
import com.example.quickbyte.API.DTO.CreateBusinessOwnerDTO;
import com.example.quickbyte.API.DTO.CreateMenuItemDTO;
import com.example.quickbyte.API.DTO.LoginRequestDTO;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.API.DTO.MenuItemDTO;
import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.API.DTO.UserCreationRequestDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.API.Services.BusinessOwnerService;
import com.example.quickbyte.API.Services.ManageMenuItemService;
import com.example.quickbyte.API.Services.MenuItemService;
import com.example.quickbyte.API.Services.UserService;
import java.util.*;

public class Facade {

    private static Facade instance;


    // Business Information
    private BusinessInfoService businessInfoService;
    private BusinessInfoDTO businessInfo = new BusinessInfoDTO();


    // Menu Item Information
    private MenuItemService menuItemService;
    private ManageMenuItemService manageMenuItemService;
    private List<MenuItem> fullMenu = new ArrayList<MenuItem>();


    // User Information
    private UserService userService;
    private UserDTO loggedInUser = new UserDTO();


    // Business Owner Information
    private BusinessOwnerService businessOwnerService;
    private BusinessOwnerDTO loggedInOwner = new BusinessOwnerDTO();


    private Facade() {
        businessInfoService = BusinessInfoService.getInstance();
        menuItemService = MenuItemService.getInstance();
        manageMenuItemService = ManageMenuItemService.getInstance();
        userService = UserService.getInstance();
        businessOwnerService = BusinessOwnerService.getInstance();
    }

    public static synchronized Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    /*Get from database methods: */
    // Used to grab information from the facade after calls to the database have been done

    public BusinessInfoDTO getBusinessInfoVar() {return businessInfo;}

    public List<MenuItem> getFullMenuVar() {return fullMenu;}

    public UserDTO getLoggedInUserVar() {return loggedInUser;}

    public BusinessOwnerDTO getLoggedInOwnerVar() {return loggedInOwner;}


    /*Update database methods: */

    // Called internally after we are retrieving an update to a business info
    private void updateBusinessInfo(BusinessInfoDTO businessInfo) {
        this.businessInfo.setBusinessName(businessInfo.getBusinessName());
        this.businessInfo.setSlogan(businessInfo.getSlogan());
        this.businessInfo.setLogoUrl(businessInfo.getLogoUrl());
        this.businessInfo.setPrimaryColor(businessInfo.getPrimaryColor());
        this.businessInfo.setSecondaryColor(businessInfo.getSecondaryColor());
    }


    // Called internally after we are retrieving an update to the full menu
    private void updateFullMenu(List<MenuItem> fullMenu){
        this.fullMenu.clear();

        for (int i = 0; i < fullMenu.size(); ++i)
        {
            MenuItem origItem = fullMenu.get(i);

            MenuItem copyItem = new MenuItem(origItem.getItemId(), origItem.getName(),
                    origItem.getDescription(), origItem.getPrice(), origItem.getImageUrl(), origItem.isAvailable());
            this.fullMenu.add(copyItem);
        }
    }

    // Called internally after we are retrieving an update to the logged in user
    private void updatedLoggedInUser(UserDTO loggedInUser)
    {
        this.loggedInUser.setUserId(loggedInUser.getUserId());
        this.loggedInUser.setUsername(loggedInUser.getUsername());
        this.loggedInUser.setEmail(loggedInUser.getEmail());
        this.loggedInUser.setFirstName(loggedInUser.getFirstName());
        this.loggedInUser.setLastName(loggedInUser.getLastName());
        this.loggedInUser.setPhoneNumber(loggedInUser.getPhoneNumber());
        this.loggedInUser.setIsActive(loggedInUser.getIsActive());
        this.loggedInUser.setLoyaltyPoints(loggedInUser.getLoyaltyPoints());
        this.loggedInUser.setCardNumber(loggedInUser.getCardNumber());
        this.loggedInUser.setExpiryMonth(loggedInUser.getExpiryMonth());
        this.loggedInUser.setExpiryYear(loggedInUser.getExpiryYear());
        this.loggedInUser.setCvv(loggedInUser.getCvv());
        this.loggedInUser.setIsDefaultCard(loggedInUser.getIsDefaultCard());
    }

    // Called internally after we are retrieving an update to the logged in owner
    private void updateLoggedInOwner(BusinessOwnerDTO loggedInOwner)
    {
        this.loggedInOwner.setOwnerId(loggedInOwner.getOwnerId());
        this.loggedInOwner.setUsername(loggedInOwner.getUsername());
        this.loggedInOwner.setEmail(loggedInOwner.getEmail());
        this.loggedInOwner.setPasswordHash(loggedInOwner.getPasswordHash());
        this.loggedInOwner.setContactNumber(loggedInOwner.getContactNumber());
        this.loggedInOwner.setCreatedAt(loggedInOwner.getCreatedAt());
    }


    // Call the database to get the latest business info
    public void getBusinessInfo(final DatabaseCallback<BusinessInfoDTO> callback) {
        businessInfoService.getBusinessInfo(1, new BusinessInfoService.ApiCallback<BusinessInfoDTO>() {
            @Override
            public void onSuccess(BusinessInfoDTO result) {
                updateBusinessInfo(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    public void saveBusinessInfo(BusinessInfoDTO businessInfo, final DatabaseCallback<BusinessInfoDTO> callback) {
        businessInfoService.saveOrUpdateBusinessInfo(businessInfo, new BusinessInfoService.ApiCallback<BusinessInfoDTO>() {
            @Override
            public void onSuccess(BusinessInfoDTO result) {
                updateBusinessInfo(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    public void getAllItems(final MenuItemService.ApiCallback<List<MenuItem>> callback) {
        menuItemService.getAllItems(new MenuItemService.ApiCallback<List<MenuItem>>() {
            @Override
            public void onSuccess(List<MenuItem> result) {
                updateFullMenu(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });

    }


    public void createMenuItem(CreateMenuItemDTO createMenuItemDTO, final ManageMenuItemService.ApiCallback<MenuItemDTO> callback) {
        manageMenuItemService.createMenuItem(createMenuItemDTO, new ManageMenuItemService.ApiCallback<MenuItemDTO>() {
            @Override
            public void onSuccess(MenuItemDTO result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void updateMenuItem(MenuItemDTO menuItemDTO, final ManageMenuItemService.ApiCallback<MenuItemDTO> callback) {
        manageMenuItemService.updateMenuItem(menuItemDTO, new ManageMenuItemService.ApiCallback<MenuItemDTO>() {
            @Override
            public void onSuccess(MenuItemDTO result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void deleteMenuItem(int itemId, final MenuItemService.ApiCallback<Void> callback) {
        manageMenuItemService.deleteMenuItem(itemId, new ManageMenuItemService.ApiCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void loginUser(String username, String password, final UserService.ApiCallback<UserDTO> callback)
    {
        userService.loginUser(username, password, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                updatedLoggedInUser(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void createUser(UserCreationRequestDTO userCreationRequest, final UserService.ApiCallback<UserDTO> callback)
    {
        userService.createUser(userCreationRequest, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                updatedLoggedInUser(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void updateUser(int userId, UserDTO userDTO, final UserService.ApiCallback<UserDTO> callback)
    {
        userService.updateUser(userId, userDTO, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                updatedLoggedInUser(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void loginBusinessOwner(LoginRequestDTO loginRequest, final BusinessOwnerService.ApiCallback<BusinessOwnerDTO> callback)
    {
        businessOwnerService.loginBusinessOwner(loginRequest, new BusinessOwnerService.ApiCallback<BusinessOwnerDTO>() {
            @Override
            public void onSuccess(BusinessOwnerDTO result) {
                updateLoggedInOwner(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void updateBusinessOwner(int ownerId, CreateBusinessOwnerDTO updateOwner, final BusinessOwnerService.ApiCallback<BusinessOwnerDTO> callback)
    {
        businessOwnerService.updateBusinessOwner(ownerId, updateOwner, new BusinessOwnerService.ApiCallback<BusinessOwnerDTO>() {
            @Override
            public void onSuccess(BusinessOwnerDTO result) {
                updateLoggedInOwner(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }



    public interface DatabaseCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }


}
