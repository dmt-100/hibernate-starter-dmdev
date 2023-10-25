package com.dmdev.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
public class User {

    @Id
    @GeneratedValue(generator =  "user_gen", strategy = GenerationType.TABLE)
//    @SequenceGenerator(name = "user_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @TableGenerator(
            name = "user_gen",
            table = "all_sequence",
            pkColumnName = "table_name",
            valueColumnName = "pk_value",
            allocationSize = 1)
    private Long id;


    @Column(unique = true)
    private String username;

    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Type(type = "dmdev")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;
}
