package com.web.jollytrips;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface MytripsRepository extends JpaRepository<MytripsModel,Integer>{
	@Query(nativeQuery=true,value="sp_fetch")
	public List<MytripsModel> alldetails();

	@Query(nativeQuery=true,value="sp_oneperson :id")
	public MytripsModel oneperson(@Param("id") int id);

	@Query(nativeQuery=true,value="sp_login :email, :password")
	public MytripsModel loginperson(@Param("email") String email, @Param("password") String password);

	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="sp_register :name, :email, :password, :mobilenumber, :gender")
	public void insertperson(@Param("name") String name,@Param("email") String email,@Param("password") String password,@Param("mobilenumber") String mobilenumber,@Param("gender") String gender);


	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="sp_update :id, :name, :email, :password, :mobilenumber, :gender")
	public void updateperson(@Param("id") int id, @Param("name") String name,@Param("email") String email,@Param("password") String password,@Param("mobilenumber") String mobilenumber,@Param("gender") String gender);


	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="sp_delete :id")
	public void deleteperson(@Param("id") int id);

}
