package com.project.UploadMultipleFiles.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "FilesDB")
public class FileEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "FileID", columnDefinition = "varchar(255)")
    private UUID id;

    @Column(name = "FileName")
    private String name;

    @Column(name = "FileExtension")
    private String contentType;

    @Lob
    private byte[] data;

    public FileEntity(String name, String contentType, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
    }

}
