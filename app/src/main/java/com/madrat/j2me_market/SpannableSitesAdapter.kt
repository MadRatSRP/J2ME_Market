package com.madrat.j2me_market

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madrat.j2me_market.model.SpannableSite
import inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_sites.*

class SpannableSitesAdapter
    : RecyclerView.Adapter<SpannableSitesAdapter.SpannableSitesHolder>() {
    private val spannableListOfSites = ArrayList<SpannableSite>()

    fun returnSpannableListOfSites() = spannableListOfSites
    fun updateSpannableListOfSites(newSpannableListOfSites: ArrayList<SpannableSite>) {
        spannableListOfSites.clear()
        spannableListOfSites.addAll(newSpannableListOfSites)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpannableSitesHolder
        = SpannableSitesHolder(parent.inflate(R.layout.list_sites))
    override fun onBindViewHolder(holder: SpannableSitesHolder, position: Int)
        = holder.bind(spannableListOfSites[position])
    override fun getItemCount(): Int
        = spannableListOfSites.size

    inner class SpannableSitesHolder internal constructor(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(spannableSite: SpannableSite) {
            sitesTitle.text = spannableSite.title
        }
    }
}