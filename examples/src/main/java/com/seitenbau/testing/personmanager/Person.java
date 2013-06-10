package com.seitenbau.testing.personmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person
{
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "persons_seq")
  @SequenceGenerator(name="persons_seq", sequenceName = "persons_seq")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column
  private String name;

  @Column(name = "job_id")
  private Integer job;

  @Column(name = "team_id")
  private Integer team;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getJob()
  {
    return job.intValue();
  }

  public void setJob(Integer job)
  {
    this.job = job;
  }

  public Integer getTeam()
  {
    return team;
  }

  public void setTeam(Integer team)
  {
    this.team = team;
  }

}