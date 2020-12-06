package com.szareckii.popularlibraries.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.szareckii.popularlibraries.mvp.model.entity.room.MIGRATION_1_2
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomGithubUser
import com.szareckii.popularlibraries.mvp.model.entity.room.dao.RepositoryDao
import com.szareckii.popularlibraries.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 3)
abstract class Database: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created")
        fun create(context: Context) {
            instance ?: let {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
//                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
            }
        }
    }
}