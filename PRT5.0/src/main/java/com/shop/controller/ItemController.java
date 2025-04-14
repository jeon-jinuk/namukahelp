package com.shop.controller;

import com.shop.dto.CocktailDto;
import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Cocktail;
import com.shop.entity.Item;
import com.shop.entity.MixItem;
import com.shop.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CocktailService cocktailService;
    private final MixItemService mixItemService;

    // 상품 관리 페이지
    @GetMapping(value = {"/", "/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto,
                             @PathVariable("page") Optional<Integer> page,
                             Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        List<Cocktail> cocktails = cocktailService.getCocktailList();
        model.addAttribute("items", items);
        model.addAttribute("cocktails", cocktails);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        return "/item/itemMng";
    }

    @GetMapping(value = {"/cocktailList"})
    public String cocktailManage(Model model) {
        List<Cocktail> cocktails = cocktailService.getCocktailList();
        log.info("cocktails size ... " + cocktails.size());
        model.addAttribute("cocktails", cocktails);
        return "/item/cocktailMng";
    }

    @GetMapping("/cocktailForm")
    public void cocktailForm() {

    }

    // 아이템 등록 페이지
    @GetMapping("/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    // 아이템 등록 페이지
    @PostMapping("/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("imgFile") List<MultipartFile> imgFileList) {
        if (bindingResult.hasErrors()) {
            return "redirect:/item/";
        }
        if (imgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("error Message", "첫번째 상품 이미지는 필수 입력값입니다.");
            return "redirect:/item/";
        }
        try {
            itemService.saveItem(itemFormDto, imgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMassage", "상품 등록중 에러가 발생하였습니다.");
            return "redirect:/item/";
        }
        return "redirect:/item/";
    }

    // 아이템 수정 페이지
    @GetMapping("/itemModify/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model) {

        try {
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다");
            model.addAttribute("itemFormDto", new ItemFormDto());
        }
        return "item/itemForm";
    }

    // 아이템 수정 페이지
    @PostMapping("/itemModify/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, @RequestParam("imgFile")
    List<MultipartFile> imgfileList, Model model) {
        log.info("modify... ID:" + itemFormDto.getId());
        if (bindingResult.hasErrors()) {
            log.info("수정 에러1");
            return "redirect:/item/";
        }
        if (imgfileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            log.info("수정 에러2");
            model.addAttribute("error Massage", "첫번째 상품 이미지는 필수 입력값입니다");
            return "redirect:/item/";
        }
        try {
            itemService.updateItem(itemFormDto, imgfileList);
        } catch (Exception e) {
            log.info("수정 에러3");
            model.addAttribute("errorMassage", "상품 등록중 에러가 발생하였습니다.");
            return "redirect:/item/";
        }
        return "redirect:/item/";

    }

    // 상세정보
    // item/(item id)
    @GetMapping("/info/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId) {
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        log.info("itemFormDto image size ...." + itemFormDto.getImgDtoList().size());
        model.addAttribute("itemFormDto", itemFormDto);
        return "test/itemDtl";
    }

    // 아이템 삭제
    @GetMapping("/itemRemove/{itemId}")
    public String deleteItem(@PathVariable("itemId") Long itemId) {
        log.info("delete... ID:" + itemId);
        itemService.deleteItem(itemId);
        return "redirect:/item/";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 상품 리스트
    // alcohol
    // material
    @GetMapping("/itemList/{type}")
    public String itemList(Model model, @PathVariable("type") String type) {
        log.info("type : " + type);
        List<ItemFormDto> itemList = itemService.getTypeList(type);
        log.info("List Type... " + itemList.size());
        model.addAttribute("itemList", itemList);
        return "item/itemList";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 레시피 등록

    @GetMapping("/cocktail/new")
    public String newCocktail(Model model) {
        List<Item> itemList = itemService.getItemList();
        model.addAttribute("cocktail", new CocktailDto());
        model.addAttribute("itemList", itemList);
        return "/item/cocktailForm";
    }

    @PostMapping("/cocktail/new")
    public String newCocktail(Model model, @Valid CocktailDto cocktailDto, @RequestParam("cImgFile") List<MultipartFile> cImgFileList,
                              @RequestParam("item") List<Item> itemList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/item/";
        }
        if (cImgFileList.get(0).isEmpty() && cocktailDto.getMenuId() == null) {
            model.addAttribute("ErrorMessage", "첫번째 이미지는 필수");
            return "redirect:/cocktail/new";
        }
        try {
            cocktailService.saveCocktail(cocktailDto, cImgFileList, itemList);

        } catch (Exception e) {
            model.addAttribute("ErrorMessage", "등록중 에러 발생");
            return "redirect:/cocktail/new";
        }
        return "redirect:/item/";
    }

    // 레시피 수정_만드는중
    @GetMapping("/cocktailModify/{menuId}")
    public String cocktailModify(Model model, @PathVariable("menuId") Cocktail menuId) {
        List<Item> itemList = itemService.getItemList();
        List<MixItem> mixItemList = mixItemService.getItemList(menuId);
        CocktailDto cocktailDto = cocktailService.getCocktailDtl(menuId);
        model.addAttribute("itemList", itemList);
        model.addAttribute("cocktail", cocktailDto);
        model.addAttribute("mixItemList", mixItemList);
        return "/item/cocktailForm";
    }

    @PostMapping("/cocktailModify/{menuId}")
    public String cocktailModify(@Valid CocktailDto cocktailDto, @RequestParam("cImgFile") List<MultipartFile> cImgFileList) throws Exception {

        cocktailService.updateCocktail(cocktailDto, cImgFileList);

        return "redirect:/item/";
    }

    // 레시피 삭제
    @GetMapping("/cocktailRemove/{menuId}")
    public String deleteCocktail(@PathVariable("menuId") Long menuId) {
        log.info("delete Cocktail... ID:" + menuId);
        cocktailService.deleteCocktail(menuId);
        return "redirect:/item/";
    }

    //    // 상세정보
    @GetMapping("/cocktail/{menuId}")
    public String itemDtl(Model model, @PathVariable("menuId") Cocktail cocktail) {
        List<MixItem> mixItemList = mixItemService.getItemList(cocktail);
        CocktailDto cocktailDto = cocktailService.getCocktailDtl(cocktail);
        model.addAttribute("cocktail", cocktailDto);
        model.addAttribute("mixItemList", mixItemList);
        return "item/cocktailDtl2";
    }

    @GetMapping("/itemList/cocktailList")
    public String itemList(Model model) {
        List<Cocktail> cocktailList = cocktailService.getCocktailList();
        log.info("List Type... " + cocktailList.size());
        model.addAttribute("cocktailList", cocktailList);
        return "item/cocktailList";
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 베이스 술 전체 리스트
//    @GetMapping("/item")
//    public String itemList(Model model) {
//        List<item> itemList = itemService.getitemList();
//        List<AImg> aImgList = aImgService.getAImgList();
//        model.addAttribute("itemList", itemList);
//        return "item/itemList";
//    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}


