package DAO;

import java.util.ArrayList;

import DTO.NGUYENLIEU;

public interface DaoInterface<T> {
	 public int insert(T t);

	 public int delete(T t);

     public int update(T t);

     public ArrayList<T> selectAll();

     public T selectById(T t);

     public ArrayList<T> selectByCondition(String condition);

	 int updateSL(String manl, int sl);


}
