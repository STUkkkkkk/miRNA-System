package stukk.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import stukk.Pojo.Mirna;

public interface MirnaService {

    public boolean save(Mirna mirna);//增加

    public boolean delete(Long id);//删除

    public boolean update(Mirna mirna);//改变

    public Page<Mirna> selectPage(Page page, Wrapper<Mirna> wrapper);//查

}
