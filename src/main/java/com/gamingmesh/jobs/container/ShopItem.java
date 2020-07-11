package com.gamingmesh.jobs.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopItem {

    private String NodeName = null;

    private double price = 0D;

    private int slot = -1;
    private int page = -1;

    private String IconMaterial = null;
    private int IconAmount = 1;
    private String IconName = null;
    private final List<String> IconLore = new ArrayList<>();

    private boolean HideWithoutPerm = false;
    private boolean hideNoEnoughPoint = false;

    private int RequiredTotalLevels = -1;

    private HashMap<String, Integer> RequiredJobs = new HashMap<>();

    private final List<String> RequiredPerm = new ArrayList<>();
    private final List<String> Commands = new ArrayList<>();
    private final List<JobItems> items = new ArrayList<>();

    private String PlayerName;
    private boolean useCurrentPlayer = false;

    public ShopItem(String NodeName, double price) {
	this.NodeName = NodeName;
	this.price = price;
    }

    public void setPage(Integer page) {
	this.page = page;
    }

    public int getPage() {
	return page;
    }

    public void setSlot(Integer slot) {
	this.slot = slot;
    }

    public int getSlot() {
	return slot;
    }

    public void setitems(List<JobItems> items) {
	this.items.clear();
	this.items.addAll(items == null ? new ArrayList<>() : items);
    }

    public List<JobItems> getitems() {
	return items;
    }

    public void setCommands(List<String> Commands) {
	this.Commands.clear();
	this.Commands.addAll(Commands == null ? new ArrayList<>() : Commands);
    }

    public List<String> getCommands() {
	return Commands;
    }

    public void setRequiredJobs(HashMap<String, Integer> RequiredJobs) {
	this.RequiredJobs = RequiredJobs;
    }

    public HashMap<String, Integer> getRequiredJobs() {
	return RequiredJobs;
    }

    public void setRequiredPerm(List<String> RequiredPerm) {
	this.RequiredPerm.clear();
	this.RequiredPerm.addAll(RequiredPerm == null ? new ArrayList<>() : RequiredPerm);
    }

    public List<String> getRequiredPerm() {
	return RequiredPerm;
    }

    public void setHideIfThereIsNoEnoughPoints(boolean hideNoEnoughPoint) {
	this.hideNoEnoughPoint = hideNoEnoughPoint;
    }

    public boolean isHideIfNoEnoughPoints() {
	return hideNoEnoughPoint;
    }

    public void setHideWithoutPerm(boolean HideWithoutPerm) {
	this.HideWithoutPerm = HideWithoutPerm;
    }

    public boolean isHideWithoutPerm() {
	return HideWithoutPerm;
    }

    public void setIconLore(List<String> IconLore) {
	this.IconLore.clear();
	this.IconLore.addAll(IconLore == null ? new ArrayList<>() : IconLore);
    }

    public List<String> getIconLore() {
	return IconLore;
    }

    public String getNodeName() {
	return NodeName;
    }

    public String getIconMaterial() {
	return IconMaterial;
    }

    public void setIconMaterial(String IconMaterial) {
	this.IconMaterial = IconMaterial;
    }

    public double getPrice() {
	return price;
    }

    public void setIconAmount(int IconAmount) {
	this.IconAmount = IconAmount;
    }

    public int getIconAmount() {
	return IconAmount;
    }

    public void setIconName(String IconName) {
	this.IconName = IconName;
    }

    public String getIconName() {
	return IconName;
    }

    public int getRequiredTotalLevels() {
	return RequiredTotalLevels;
    }

    public void setRequiredTotalLevels(int RequiredTotalLevels) {
	this.RequiredTotalLevels = RequiredTotalLevels;
    }

    public String getCustomHead() {
	return PlayerName;
    }

    public void setCustomHead(String PlayerName) {
	this.PlayerName = PlayerName;
    }

    public boolean isHeadOwner() {
	return useCurrentPlayer;
    }

    public void setCustomHeadOwner(boolean useCurrentPlayer) {
	this.useCurrentPlayer = useCurrentPlayer;
    }
}
