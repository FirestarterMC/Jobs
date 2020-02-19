/**
 * Copyright (C) 2017 Zrips
 */

package com.gamingmesh.jobs.CMILib;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.gamingmesh.jobs.Jobs;

public class Reflections {

    private Class<?> CraftServerClass;
    private Object CraftServer;

    private static Class<?> NBTTagCompound;
    private Class<?> NBTBase;
//    private Class<?> NBTTagList;

    private static Class<?> CraftItemStack;
//    private Class<?> Item;
    private static Class<?> IStack;

    public Reflections() {
	initialize();
    }

    private void initialize() {
	try {
	    CraftServerClass = getBukkitClass("CraftServer");
	    CraftServer = CraftServerClass.cast(Bukkit.getServer());
	    NBTTagCompound = getMinecraftClass("NBTTagCompound");
	    NBTBase = getMinecraftClass("NBTBase");
	/*try {
	    NBTTagList = getMinecraftClass("NBTTagList");
	} catch (ClassNotFoundException | SecurityException | IllegalArgumentException e) {
	    e.printStackTrace();
	}*/
	    CraftItemStack = getBukkitClass("inventory.CraftItemStack");
	/*try {
	    Item = getMinecraftClass("Item");
	} catch (ClassNotFoundException | SecurityException | IllegalArgumentException e) {
	    e.printStackTrace();
	}*/
	    IStack = getMinecraftClass("ItemStack");
	} catch (ClassCastException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    private static Class<?> getBukkitClass(String nmsClassString) throws ClassNotFoundException {
	return Class.forName("org.bukkit.craftbukkit." + Jobs.getVersionCheckManager().getVersion() + "." + nmsClassString);
    }

    public static Class<?> getMinecraftClass(String nmsClassString) throws ClassNotFoundException {
	return Class.forName("net.minecraft.server." + Jobs.getVersionCheckManager().getVersion() + "." + nmsClassString);
    }

    public ItemStack removeNbt(ItemStack item, String base, String path) {
	if (item == null)
	    return null;
	try {
	    Object nmsStack = asNMSCopy(item);
	    Method methTag = nmsStack.getClass().getMethod("getTag");
	    Object tag = methTag.invoke(nmsStack);
	    if (tag == null)
		return item;

	    Method compountMeth = tag.getClass().getMethod("getCompound", String.class);
	    Object compountTag = compountMeth.invoke(tag, base);

	    if (compountTag == null)
		return item;

	    Method meth = compountTag.getClass().getMethod("remove", String.class);
	    meth.invoke(compountTag, path);

	    Method mm = tag.getClass().getMethod("set", String.class, NBTBase);
	    mm.invoke(tag, base, compountTag);

	    Method meth2 = nmsStack.getClass().getMethod("setTag", NBTTagCompound);
	    meth2.invoke(nmsStack, tag);
	    return (ItemStack) asBukkitCopy(nmsStack);
	} catch (Throwable e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public ItemStack removeNbt(ItemStack item, String path) {
	if (item == null)
	    return null;
	try {
	    Object nmsStack = asNMSCopy(item);
	    Method methTag = nmsStack.getClass().getMethod("getTag");
	    Object tag = methTag.invoke(nmsStack);
	    if (tag == null)
		return item;

	    Method meth = tag.getClass().getMethod("remove", String.class);
	    meth.invoke(tag, path);

	    Method meth2 = nmsStack.getClass().getMethod("setTag", NBTTagCompound);
	    meth2.invoke(nmsStack, tag);
	    return (ItemStack) asBukkitCopy(nmsStack);
	} catch (Throwable e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public boolean hasNbt(ItemStack item, String base) {
	if (item == null)
	    return false;
	try {
	    Object nbt = getNbt(item);
	    if (nbt == null)
		return false;
	    Method meth = nbt.getClass().getMethod("getCompound", String.class);
	    Object res = meth.invoke(nbt, base);
	    return res != null;
	} catch (Throwable e) {
	    return false;
	}
    }

    public boolean hasNbtString(ItemStack item, String base) {
	if (item == null)
	    return false;
	try {
	    Object nbt = getNbt(item);
	    if (nbt == null)
		return false;
	    Method meth = nbt.getClass().getMethod("getString", String.class);
	    Object res = meth.invoke(nbt, base);
	    return res != null;
	} catch (Throwable e) {
	    return false;
	}
    }

    public static Object getNbt(ItemStack item) {
	if (item == null)
	    return null;
	try {
	    Object nmsStack = asNMSCopy(item);
	    Method methTag = nmsStack.getClass().getMethod("getTag");
	    Object tag = methTag.invoke(nmsStack);
	    return tag;
	} catch (Throwable e) {
	    return null;
	}
    }

    public Object getNbt(ItemStack item, String base, String path) {
	if (item == null)
	    return null;
	try {
	    Object nbt = getNbt(item);
	    if (nbt == null)
		return null;

	    Method compoundMeth = nbt.getClass().getMethod("getCompound", String.class);
	    Object compoundRes = compoundMeth.invoke(nbt, base);

	    if (compoundRes == null)
		return null;

	    Method meth = compoundRes.getClass().getMethod("getString", String.class);
	    Object res = meth.invoke(compoundRes, path);
	    return res;
	} catch (Throwable e) {
	    return null;
	}
    }

//
//    public ItemStack setNbt(ItemStack item, String base, String path, String value) {
//	if (item == null)
//	    return null;
//	try {
//	    Object nmsStack = asNMSCopy(item);
//	    Method methTag = nmsStack.getClass().getMethod("getTag");
//	    Object tag = methTag.invoke(nmsStack);
//	    if (tag == null)
//		tag = NBTTagCompound.newInstance();
//
//	    Method compountMeth = tag.getClass().getMethod("getCompound", String.class);
//	    Object compountTag = compountMeth.invoke(tag, base);
//
//	    if (compountTag == null)
//		compountTag = NBTTagCompound.newInstance();
//
//	    Method meth = compountTag.getClass().getMethod("setString", String.class, String.class);
//	    meth.invoke(compountTag, path, value);
//
//	    Method mm = tag.getClass().getMethod("set", String.class, NBTBase);
//	    mm.invoke(tag, base, compountTag);
//
//	    Method meth2 = nmsStack.getClass().getMethod("setTag", NBTTagCompound);
//	    meth2.invoke(nmsStack, tag);
//	    return (ItemStack) asBukkitCopy(nmsStack);
//	} catch (Throwable e) {
//	    e.printStackTrace();
//	    return null;
//	}
//    }
    public static ItemStack setNbt(ItemStack item, String path, String value) {
	if (item == null)
	    return null;
	try {
	    Object nmsStack = asNMSCopy(item);
	    if (nmsStack == null)
		return item;
	    Method methTag = nmsStack.getClass().getMethod("getTag");
	    Object tag = methTag.invoke(nmsStack);
	    if (tag == null)
		tag = NBTTagCompound.newInstance();
	    Method meth = tag.getClass().getMethod("setString", String.class, String.class);
	    meth.invoke(tag, path, value);
	    Method meth2 = nmsStack.getClass().getMethod("setTag", NBTTagCompound);
	    meth2.invoke(nmsStack, tag);
	    return (ItemStack) asBukkitCopy(nmsStack);
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static Object getNbt(ItemStack item, String path) {
	if (item == null)
	    return null;
	try {
	    Object nbt = getNbt(item);
	    if (nbt == null)
		return null;

	    Method meth = nbt.getClass().getMethod("getString", String.class);
	    Object res = meth.invoke(nbt, path);
	    return res;
	} catch (Throwable e) {
	    return null;
	}
    }

    public static Object asNMSCopy(ItemStack item) {
	try {
	    Method meth = CraftItemStack.getMethod("asNMSCopy", ItemStack.class);
	    return meth.invoke(CraftItemStack, item);
	} catch (Throwable e) {
	    return null;
	}
    }

    public static Object asBukkitCopy(Object item) {
	try {
	    Method meth = CraftItemStack.getMethod("asBukkitCopy", IStack);
	    return meth.invoke(CraftItemStack, item);
	} catch (Throwable e) {
	    return null;
	}
    }

    public Object getCraftServer() {
	return CraftServer;
    }

}
