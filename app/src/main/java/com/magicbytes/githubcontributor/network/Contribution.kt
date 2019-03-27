package com.magicbytes.githubcontributor.network

data class Contribution(val total: Int, val author: Author, val weeks: List<ContributionWeek>)
