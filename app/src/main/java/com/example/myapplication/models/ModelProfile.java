package com.example.myapplication.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ModelProfile implements Serializable {

    public ArrayList getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList preferences) {
        this.preferences = preferences;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }

    public HashMap getWork() {
        return work;
    }

    public void setWork(HashMap work) {
        this.work = work;
    }

    public String getLast_seen_window() {
        return last_seen_window;
    }

    public void setLast_seen_window(String last_seen_window) {
        this.last_seen_window = last_seen_window;
    }

    public Boolean getHas_active_subscription() {
        return has_active_subscription;
    }

    public void setHas_active_subscription(Boolean has_active_subscription) {
        this.has_active_subscription = has_active_subscription;
    }

    public String getVerification_status() {
        return verification_status;
    }

    public void setVerification_status(String verification_status) {
        this.verification_status = verification_status;
    }

    public ArrayList getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList photos) {
        this.photos = photos;
    }

    public HashMap getGeneral_information() {
        return general_information;
    }

    public void setGeneral_information(HashMap general_information) {
        this.general_information = general_information;
    }

    ArrayList preferences;
    String last_seen;
    HashMap work;
    String last_seen_window;
    Boolean has_active_subscription;
    String verification_status;
    ArrayList photos;
    HashMap general_information;
    String latest_poll;
    String lng;
    String poll_info;
    ArrayList user_interests;
    Boolean show_concierge_badge;
    Double approved_time;
    ArrayList profile_data_list;
    String instagram_images;
    Integer online_code;
    Boolean is_facebook_data_fetched;
    String icebreakers;
    String meetup;
    String lat;
    String story;

    public String getLatest_poll() {
        return latest_poll;
    }

    public void setLatest_poll(String latest_poll) {
        this.latest_poll = latest_poll;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPoll_info() {
        return poll_info;
    }

    public void setPoll_info(String poll_info) {
        this.poll_info = poll_info;
    }

    public ArrayList getUser_interests() {
        return user_interests;
    }

    public void setUser_interests(ArrayList user_interests) {
        this.user_interests = user_interests;
    }

    public Boolean getShow_concierge_badge() {
        return show_concierge_badge;
    }

    public void setShow_concierge_badge(Boolean show_concierge_badge) {
        this.show_concierge_badge = show_concierge_badge;
    }

    public Double getApproved_time() {
        return approved_time;
    }

    public void setApproved_time(Double approved_time) {
        this.approved_time = approved_time;
    }

    public ArrayList getProfile_data_list() {
        return profile_data_list;
    }

    public void setProfile_data_list(ArrayList profile_data_list) {
        this.profile_data_list = profile_data_list;
    }

    public String getInstagram_images() {
        return instagram_images;
    }

    public void setInstagram_images(String instagram_images) {
        this.instagram_images = instagram_images;
    }

    public Integer getOnline_code() {
        return online_code;
    }

    public void setOnline_code(Integer online_code) {
        this.online_code = online_code;
    }

    public Boolean getIs_facebook_data_fetched() {
        return is_facebook_data_fetched;
    }

    public void setIs_facebook_data_fetched(Boolean is_facebook_data_fetched) {
        this.is_facebook_data_fetched = is_facebook_data_fetched;
    }

    public String getIcebreakers() {
        return icebreakers;
    }

    public void setIcebreakers(String icebreakers) {
        this.icebreakers = icebreakers;
    }

    public String getMeetup() {
        return meetup;
    }

    public void setMeetup(String meetup) {
        this.meetup = meetup;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
