package com.hnjz.app.work.manager;

import java.io.Serializable;

import com.hnjz.wf.dao.MyDao;
import com.hnjz.wf.entity.CommonPo;

public abstract class AbsManagerImpl<T extends CommonPo, S extends Serializable> implements
                                                                                 AbsManager {
    private MyDao<T, S> dao;

    public void setDao(MyDao<T, S> dao) {
        this.dao = dao;
    }

    /**
     * ��ѯ����
     * @param id
     * @return
     * @throws Exception
     */
    public T get(S id) throws Exception {
        return dao.get(id);
    }

    /**
     * �������
     * @param t
     * @throws Exception
     */
    public void save(T t) throws Exception {
        dao.save(t);
    }

    /**
     * ɾ��
     * @param id
     * @throws Exception
     */
    public void remove(S id) throws Exception {
        dao.remove(get(id));
    }

    /**
     * ɾ��
     * @param t
     * @throws Exception
     */
    public void remove(T t) throws Exception {
        dao.remove(t);
    }
}