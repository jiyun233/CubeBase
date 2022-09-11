package cn.origin.cube.managers

import cn.origin.cube.utils.client.ChatUtil
import net.minecraft.entity.player.EntityPlayer

class FriendManager {
    private val friendList = arrayListOf<String>()
    fun isFriend(name: String): Boolean {
        return friendList.contains(name)
    }

    fun isFriend(player: EntityPlayer): Boolean {
        return isFriend(player.name)
    }

    fun getAllFriend(): ArrayList<String> {
        return friendList
    }

    fun add(name: String) {
        if (!friendList.contains(name)) {
            friendList.add(name)
            ChatUtil.sendMessage("&e$name &6has been friend")
        }
    }

    fun add(player: EntityPlayer) {
        add(player.name)
    }

    fun remove(name: String) {
        if (friendList.contains(name)) {
            friendList.remove(name)
            ChatUtil.sendMessage("&e$name &6has been unfriend")
        }
    }

    fun remove(player: EntityPlayer) {
        remove(player.name)
    }
}