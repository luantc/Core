package com.luantc.core.api



object ApiSettings {
    const val PATH_ORGANIZATION = "organization11111"
    const val PARAM_REPOS_TYPE = "type"

    const val PATH_REPO = "repo"
    const val PATH_OWNER = "owner"

    const val PATH_COMMENT = "comments"


    const val ORGANIZATION_REPOS = "orgs/{$PATH_ORGANIZATION}/repos"
    const val REPOSITORY = "/repos/{$PATH_OWNER}/{$PATH_REPO}"

    const val COMMENT= "{$PATH_COMMENT}"
}

enum class ApiTarget {
    COMMENT
}