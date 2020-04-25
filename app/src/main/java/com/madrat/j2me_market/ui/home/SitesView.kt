package com.madrat.j2me_market.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.madrat.j2me_market.R
import com.madrat.j2me_market.SpannableSitesAdapter
import com.madrat.j2me_market.model.Site
import com.madrat.j2me_market.model.SpannableSite
import kotlinx.android.synthetic.main.fragment_sites.*
import kotlinx.android.synthetic.main.fragment_sites.view.*
import linearManager

class SitesView : Fragment() {
    lateinit var adapter: SpannableSitesAdapter
    lateinit var listOfSpannableSites: ArrayList<SpannableSite>

    private val spanHighlight by lazy {
        ForegroundColorSpan(
            ResourcesCompat.getColor(resources, R.color.colorAccent, null)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sites,
            container, false)

        adapter = SpannableSitesAdapter()

        rootView.sitesRecyclerView.linearManager()
        rootView.sitesRecyclerView.adapter = adapter
        return rootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateAndShowListOfSites(arrayListOf(
            Site("koshka"), Site("sobaka"), Site("ptichka")))
        sitesSearchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int,
                                           count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int,
                                       before: Int, count: Int) {
                updateSearch()
                highlight()
            }
        })
    }


    fun updateAndShowListOfSites(listOfSites: ArrayList<Site>) {
        listOfSpannableSites = ArrayList()
        val spannableListOfCheats = ArrayList<SpannableSite>()
        listOfSites.forEach {
            spannableListOfCheats.add(
                SpannableSite(it.title)
            )
        }
        this.listOfSpannableSites.addAll(spannableListOfCheats)
        showListOfCheats()
    }
    fun updateListOfSites(listOfSpannableSites: ArrayList<SpannableSite>) {
        adapter.updateSpannableListOfSites(listOfSpannableSites)
        sitesRecyclerView.adapter = adapter
    }

    fun showListOfCheats() {
        updateListOfSites(this.listOfSpannableSites)
    }
    fun showListOfCheats(updatedListOfSites: ArrayList<SpannableSite>) {
        updateListOfSites(updatedListOfSites)
    }

    private fun updateSearch() {
        val siteTitle = sitesSearchBar.text

        if (siteTitle?.length == 0) {
            showListOfCheats()
        } else {
            searchForCheatAndUpdateListOfCheats(siteTitle.toString())
        }
    }

    fun searchForCheatAndUpdateListOfCheats(cheatName: String) {
        val updatedListOfCheats = listOfSpannableSites.filter {
            it.title.contains(cheatName, true)
        } as ArrayList<SpannableSite>
        showListOfCheats(updatedListOfCheats)
    }

    private fun highlight() {
        val cheatName = sitesSearchBar.text
        adapter.returnSpannableListOfSites().forEach { site ->
            site.title.getSpans(0, site.title.length, ForegroundColorSpan::class.java).forEach {
                site.title.removeSpan(it)
            }
            if (site.title.contains(cheatName, true)) {
                val index = site.title.toString().indexOf(cheatName.toString(), 0, true)
                site.title.setSpan(
                    spanHighlight,
                    index,
                    index + cheatName.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }
}