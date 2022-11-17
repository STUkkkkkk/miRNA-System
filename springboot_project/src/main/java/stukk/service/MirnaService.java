package stukk.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import stukk.Pojo.Mirna;
import stukk.config.Result;

public interface MirnaService extends IService<Mirna> {

    public boolean save(Mirna mirna);//增加

    public boolean delete(Long id);//删除

    public boolean update(Mirna mirna);//改变

    public Page<Mirna> selectPage(Page page, Wrapper<Mirna> wrapper);//查

    Result getAll();
}
