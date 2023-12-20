package com.pool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GitResponse {

        @JsonProperty("id")
        private float id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("private")
        private boolean isPrivate;
        @JsonProperty("OwnerObject")
        Owner OwnerObject;
        @JsonProperty("html_url")
        private String html_url;
        @JsonProperty("description")
        private String description;
        @JsonProperty("fork")
        private boolean fork;
        @JsonProperty("url")
        private String url;
        @JsonProperty("forks_url")
        private String forks_url;
        @JsonProperty("keys_url")
        private String keys_url;
        @JsonProperty("collaborators_url")
        private String collaborators_url;
        @JsonProperty("teams_url")
        private String teams_url;
        @JsonProperty("hooks_url")
        private String hooks_url;
        @JsonProperty("issue_events_url")
        private String issue_events_url;
        @JsonProperty("events_url")
        private String events_url;
        @JsonProperty("assignees_url")
        private String assignees_url;
        @JsonProperty("branches_url")
        private String branches_url;
        @JsonProperty("tags_url")
        private String tags_url;
        @JsonProperty("blobs_url")
        private String blobs_url;
        @JsonProperty("blobs_url")
        private String git_tags_url;
        @JsonProperty("blobs_url")
        private String git_refs_url;
    @JsonProperty("blobs_url")
        private String trees_url;
    @JsonProperty("blobs_url")
        private String statuses_url;
    @JsonProperty("blobs_url")
        private String languages_url;
    @JsonProperty("blobs_url")
        private String stargazers_url;
    @JsonProperty("blobs_url")

        private String contributors_url;
    @JsonProperty("blobs_url")
        private String subscribers_url;
    @JsonProperty("blobs_url")
        private String subscription_url;
    @JsonProperty("blobs_url")
        private String commits_url;
    @JsonProperty("blobs_url")
        private String git_commits_url;
    @JsonProperty("blobs_url")
        private String comments_url;
        private String issue_comment_url;
        private String contents_url;
        private String compare_url;
        private String merges_url;
        private String archive_url;
        private String downloads_url;
        private String issues_url;
        private String pulls_url;
        private String milestones_url;
        private String notifications_url;
        private String labels_url;
        private String releases_url;
        private String deployments_url;
    @JsonProperty("default_branch")
        private String created_at;

    @JsonProperty("default_branch")
        private String updated_at;
    @JsonProperty("default_branch")
        private String pushed_at;
    @JsonProperty("default_branch")
        private String git_url;
    @JsonProperty("default_branch")
        private String ssh_url;
    @JsonProperty("default_branch")
        private String clone_url;
    @JsonProperty("default_branch")
        private String svn_url;
    @JsonProperty("default_branch")
        private String homepage = null;
    @JsonProperty("default_branch")
        private float size;
    @JsonProperty("default_branch")
        private float stargazers_count;
    @JsonProperty("default_branch")
        private float watchers_count;
    @JsonProperty("default_branch")
        private String language;
    @JsonProperty("default_branch")
        private boolean has_issues;
    @JsonProperty("default_branch")
        private boolean has_projects;
    @JsonProperty("default_branch")
        private boolean has_downloads;
    @JsonProperty("default_branch")
        private boolean has_wiki;
    @JsonProperty("default_branch")
        private boolean has_pages;
    @JsonProperty("default_branch")
        private boolean has_discussions;
    @JsonProperty("default_branch")
        private float forks_count;
    @JsonProperty("default_branch")
        private String mirror_url = null;
    @JsonProperty("default_branch")
        private boolean archived;
    @JsonProperty("default_branch")
        private boolean disabled;
    @JsonProperty("default_branch")
        private float open_issues_count;
    @JsonProperty("default_branch")
        private String license = null;
    @JsonProperty("default_branch")
        private boolean allow_forking;
    @JsonProperty("default_branch")
        private boolean is_template;
    @JsonProperty("default_branch")
        private boolean web_commit_signoff_required;
    @JsonProperty("default_branch")
        ArrayList<Object> topics = new ArrayList<Object>();
    @JsonProperty("default_branch")
        private String visibility;
    @JsonProperty("default_branch")
        private float forks;
    @JsonProperty("default_branch")
        private float open_issues;
    @JsonProperty("default_branch")
        private float watchers;
    @JsonProperty("default_branch")
        private String defaultBranch;
    @JsonProperty("PermissionsObject")
        private Permissions permissionsObject;


}
