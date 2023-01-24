package com.example.a6monthproject.model

data class PlayList(
    var etag: String?, // Y5R4w7fIcYla05_LWz1BVrX-2ng
    var items: List<Item?>?,
    var kind: String?, // youtube#playlistListResponse
    var nextPageToken: String?, // CAUQAA
    var pageInfo: PageInfo?
)