package com.example.lostfoundconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast

class SessionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Sample data
        val userProfile = UserProfile("John Doe", R.drawable.profile_picture)
        val posts = mutableListOf(
            Post(userProfile, "Lost Item Category 1", "Description for post 1"),
            Post(userProfile, "Lost Item Category 2", "Description for post 2"),
            // Add more posts here
        )

//        // Set user profile data
//        val profilePicture
//        profilePicture.setImageResource(userProfile.profilePicture)
//        postUsername.text = userProfile.username
//
//        // Set up posts
//        posts.forEach { post ->
//            val postView = layoutInflater.inflate(R.layout.post_item, null)
//            postView.postUserProfilePicture.setImageResource(post.userProfile.profilePicture)
//            postView.postUsername.text = post.userProfile.username
//            postView.lostItemCategory.text = post.lostItemCategory
//            postView.postDescription.text = post.description
//
//            postView.likeButton.setOnClickListener {
//                // Handle like button click
//                Toast.makeText(this, "Liked post: ${post.description}", Toast.LENGTH_SHORT).show()
//            }
//
//            postView.commentButton.setOnClickListener {
//                // Handle comment button click
//                Toast.makeText(this, "Commented on post: ${post.description}", Toast.LENGTH_SHORT).show()
//            }
//
//            postView.shareButton.setOnClickListener {
//                // Handle share button click
//                Toast.makeText(this, "Shared post: ${post.description}", Toast.LENGTH_SHORT).show()
//            }
//
//            val userPostsContainer
//            userPostsContainer.addView(postView)
//        }
//
//        // Create Post button click listener
//        createPostButton.setOnClickListener {
//            // Handle create post button click
//            Toast.makeText(this, "Create Post button clicked", Toast.LENGTH_SHORT).show()
//        }
    }
}

data class UserProfile(val username: String, val profilePicture: Int)
data class Post(val userProfile: UserProfile, val lostItemCategory: String, val description: String)