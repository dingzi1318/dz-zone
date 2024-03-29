package com.user.mapper;

import com.user.model.JobMapping;
import com.user.model.JobMappingExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobMappingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    long countByExample(JobMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int deleteByExample(JobMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int insert(JobMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int insertSelective(JobMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    List<JobMapping> selectByExample(JobMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    JobMapping selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") JobMapping record, @Param("example") JobMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") JobMapping record, @Param("example") JobMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(JobMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job_mapping_1
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(JobMapping record);
}