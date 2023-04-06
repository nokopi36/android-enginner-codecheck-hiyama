/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import jp.co.yumemi.android.code_check.TopActivity.Companion.lastSearchDate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.util.*

/**
 * OneFragment で使う
 */
class OneViewModel(
    val context: Context
) : ViewModel() {

    // 検索結果
    fun searchResults(inputText: String): List<Item> = runBlocking {
        val client = HttpClient(Android)

        return@runBlocking GlobalScope.async {
            val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
                header("Accept", "application/vnd.github.v3+json")
                parameter("q", inputText)
            }

            val jsonBody = JSONObject(response.receive<String>())

            val jsonItems = jsonBody.optJSONArray("items")

            val items = mutableListOf<Item>()

            // アイテムの個数分ループする
            if (jsonItems != null) {
                for (i in 0 until jsonItems.length()) {
                    val jsonItem = jsonItems.optJSONObject(i)
                    val name = jsonItem?.optString("full_name") ?: "no name"
                    val ownerIconUrl = jsonItem?.optJSONObject("owner")?.optString("avatar_url") ?: "no owner"
                    val language = jsonItem?.optString("language") ?: "no language"
                    val stargazersCount = jsonItem?.optLong("stargazers_count") ?: 0
                    val watchersCount = jsonItem?.optLong("watchers_count") ?: 0
                    val forksCount = jsonItem?.optLong("forks_conut") ?: 0
                    val openIssuesCount = jsonItem?.optLong("open_issues_count") ?: 0

                    items.add(
                        Item(
                            name = name,
                            ownerIconUrl = ownerIconUrl,
                            language = context.getString(R.string.written_language, language),
                            stargazersCount = stargazersCount,
                            watchersCount = watchersCount,
                            forksCount = forksCount,
                            openIssuesCount = openIssuesCount
                        )
                    )
                }
            }

            lastSearchDate = Date()

            return@async items.toList()
        }.await()
    }
}

@Parcelize
data class Item(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable