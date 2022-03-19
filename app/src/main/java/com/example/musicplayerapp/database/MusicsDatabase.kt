package com.example.musicplayerapp.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.musicplayerapp.model.Music
import com.example.musicplayerapp.model.MusicItem
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.DELETE

@Database(entities = [MusicItem::class], version = 1)
abstract class MusicsDatabase : RoomDatabase() {

    abstract fun getMusicsDao():MusicsDao
}

@Dao
interface MusicsDao{

    @Insert(onConflict = REPLACE)
    fun insertMusic(musicItem: MusicItem): Completable

    @Insert
    fun insertAllMusics(musics:List<MusicItem>): Completable

    @Query("SELECT * FROM MusicItem WHERE collectionId=:collecId")
    fun getMusicById(collecId: Int): Single<MusicItem>

    @Query("SELECT * FROM MusicItem")
    fun getAllMusics(): Single<List<MusicItem>>

    @Delete
    fun deleteMusic(musicItem: MusicItem): Completable

}