package com.example.catatanku.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tb_catatan")
data class Catatan(
    @PrimaryKey(autoGenerate = true) var uid: Int?= null,
    @ColumnInfo(name = "judul") val judul: String?,
    @ColumnInfo(name = "deskripsi") val deskripsi: String?
) : Serializable
