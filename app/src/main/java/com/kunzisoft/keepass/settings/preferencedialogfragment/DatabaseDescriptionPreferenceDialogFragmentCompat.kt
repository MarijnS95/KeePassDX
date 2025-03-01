/*
 * Copyright 2019 Jeremy Jamet / Kunzisoft.
 *
 * This file is part of KeePassDX.
 *
 *  KeePassDX is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  KeePassDX is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePassDX.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.kunzisoft.keepass.settings.preferencedialogfragment

import android.os.Bundle
import com.kunzisoft.keepass.database.element.Database

class DatabaseDescriptionPreferenceDialogFragmentCompat : DatabaseSavePreferenceDialogFragmentCompat() {

    override fun onDatabaseRetrieved(database: Database?) {
        super.onDatabaseRetrieved(database)
        inputText = database?.description ?: ""
    }

    override fun onDialogClosed(database: Database?, positiveResult: Boolean) {
        super.onDialogClosed(database, positiveResult)
        database?.let {
            if (positiveResult) {
                val newDescription = inputText
                val oldDescription = database.description
                database.description = newDescription
                saveDescription(oldDescription, newDescription)
            }
        }
    }

    companion object {

        fun newInstance(key: String): DatabaseDescriptionPreferenceDialogFragmentCompat {
            val fragment = DatabaseDescriptionPreferenceDialogFragmentCompat()
            val bundle = Bundle(1)
            bundle.putString(ARG_KEY, key)
            fragment.arguments = bundle

            return fragment
        }
    }
}
