package com.likeminds.feedsx.post.model

import android.os.Parcelable
import com.likeminds.feedsx.utils.model.*
import kotlinx.parcelize.Parcelize

@Parcelize
class PostViewData private constructor(
    var id: String,
    var text: String,
    var attachments: List<AttachmentViewData>,
    var communityId: Int,
    var isPinned: Boolean,
    var isSaved: Boolean,
    var userId: String,
    var likesCount: Int,
    var commentsCount: Int,
    var menuItems: List<MenuItemsViewData>,
    var comments: List<CommentsViewData>,
    var createdAt: Long,
    var updatedAt: Long,
    var users: UserViewData
) : Parcelable, BaseViewType {

    //TODO: For Link?
    override val viewType: Int
        get() = when {
            (attachments.size == 1 && attachments[0].fileType == 1) -> {
                ITEM_POST_SINGLE_IMAGE
            }
            (attachments.size == 1 && attachments[0].fileType == 2) -> {
                ITEM_POST_SINGLE_VIDEO
            }
            (attachments.isNotEmpty() && attachments[0].fileType == 3) -> {
                ITEM_POST_DOCUMENTS
            }
            (attachments.size > 1 && (attachments[0].fileType == 1 || attachments[0].fileType == 2)) -> {
                ITEM_MULTIPLE_MEDIA_VIDEO
            }
            else -> {
                ITEM_POST_TEXT_ONLY
            }
        }

    class Builder {
        private var id: String = ""
        private var text: String = ""
        private var attachments: List<AttachmentViewData> = listOf()
        private var communityId: Int = 0
        private var isPinned: Boolean = false
        private var isSaved: Boolean = false
        private var userId: String = ""
        private var likesCount: Int = 0
        private var commentsCount: Int = 0
        private var menuItems: List<MenuItemsViewData> = listOf()
        private var comments: List<CommentsViewData> = listOf()
        private var createdAt: Long = 0
        private var updatedAt: Long = 0
        private var users: UserViewData = UserViewData.Builder().build()

        fun id(id: String) = apply { this.id = id }
        fun text(text: String) = apply { this.text = text }
        fun attachments(attachments: List<AttachmentViewData>) =
            apply { this.attachments = attachments }

        fun communityId(communityId: Int) = apply { this.communityId = communityId }
        fun isPinned(isPinned: Boolean) = apply { this.isPinned = isPinned }
        fun isSaved(isSaved: Boolean) = apply { this.isSaved = isSaved }
        fun userId(userId: String) = apply { this.userId = userId }
        fun likesCount(likesCount: Int) = apply { this.likesCount = likesCount }
        fun commentsCount(commentsCount: Int) = apply { this.commentsCount = commentsCount }
        fun menuItems(menuItems: List<MenuItemsViewData>) = apply { this.menuItems = menuItems }
        fun comments(comments: List<CommentsViewData>) = apply { this.comments = comments }
        fun createdAt(createdAt: Long) = apply { this.createdAt = createdAt }
        fun updatedAt(updatedAt: Long) = apply { this.updatedAt = updatedAt }
        fun users(users: UserViewData) = apply { this.users = users }

        fun build() = PostViewData(
            id,
            text,
            attachments,
            communityId,
            isPinned,
            isSaved,
            userId,
            likesCount,
            commentsCount,
            menuItems,
            comments,
            createdAt,
            updatedAt,
            users
        )
    }

    fun toBuilder(): Builder {
        return Builder().id(id)
            .text(text)
            .attachments(attachments)
            .communityId(communityId)
            .isPinned(isPinned)
            .isSaved(isSaved)
            .userId(userId)
            .likesCount(likesCount)
            .commentsCount(commentsCount)
            .menuItems(menuItems)
            .comments(comments)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .users(users)
    }
}