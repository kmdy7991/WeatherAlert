package com.weather.freind

import com.weather.friend.entity.Friend
import java.util.UUID

internal class FriendTest {
    fun friendCreateTest(){
        var friend: Friend = Friend(null, UUID.randomUUID().toString());
    }
}