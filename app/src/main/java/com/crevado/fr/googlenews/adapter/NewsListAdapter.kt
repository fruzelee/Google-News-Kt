package com.crevado.fr.googlenews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.crevado.fr.googlenews.BR
import com.crevado.fr.googlenews.R
import com.crevado.fr.googlenews.databinding.ItemNewsBinding
import com.crevado.fr.googlenews.model.NewsData
/**
 * Created by FazLe Rabbi on 13,June,2020
 * fazle.rabbi.cse@gmail.com
 * <p>
 * Copyright (c) 2020 FazLe Rabbi
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class NewsListAdapter(private var context: Context, private var newsList: ArrayList<NewsData>) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    lateinit var onItemClick: ((String) -> Unit?)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemNewsBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_news, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    class ViewHolder(
        val binding: ItemNewsBinding, private val onItemClick: (String) -> Unit?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.news, data)
            binding.executePendingBindings()

            binding.clRoot.setOnClickListener {
                onItemClick.invoke(binding.news?.url.toString())
            }
        }
    }
}