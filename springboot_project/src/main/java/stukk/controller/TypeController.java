package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.Type;
import stukk.config.Result;
import stukk.mapper.TypeMapper;
import stukk.service.TypeService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {

    @Resource
    TypeService typeService;

    @GetMapping
    public Result GetAll(@RequestParam String typeSearch){
        List<Type> list = typeService.query().like("type_name",typeSearch).list();
        return Result.success(list);
    }

}
