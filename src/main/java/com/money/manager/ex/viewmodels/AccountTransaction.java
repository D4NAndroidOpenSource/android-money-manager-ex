/*
 * Copyright (C) 2012-2015 The Android Money Manager Ex Project Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.money.manager.ex.viewmodels;

import android.database.Cursor;
import android.database.DatabaseUtils;

import com.money.manager.ex.Constants;
import com.money.manager.ex.core.Query;
import com.money.manager.ex.core.TransactionStatuses;
import com.money.manager.ex.core.TransactionTypes;
import com.money.manager.ex.database.QueryAllData;
import com.money.manager.ex.domainmodel.EntityBase;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import info.javaperformance.money.Money;

/**
 * Record from All Data query. Used for account transactions lists (search results, account
 * transactions).
 * Source is QueryAllData.
 */
public class AccountTransaction
    extends EntityBase {

    @Override
    public void loadFromCursor(Cursor c) {
        super.loadFromCursor(c);

        // Reload all money values.
        DatabaseUtils.cursorDoubleToCursorValues(c, QueryAllData.Amount, this.contentValues);
        DatabaseUtils.cursorDoubleToCursorValues(c, QueryAllData.ToAmount, this.contentValues);
    }

    public Integer getId() {
        return getInt(QueryAllData.ID);
    }

    public Integer getAccountId() {
        return getInt(QueryAllData.ACCOUNTID);
    }

    public String getAccountName() {
        return getString(QueryAllData.AccountName);
    }

    public Money getAmount() {
        return getMoney(QueryAllData.Amount);
    }

    public String getCategory() {
        return getString(QueryAllData.Category);
    }

    public String getDateString() {
        return getString(QueryAllData.Date);
    }

    public Date getDate() throws ParseException {
        String dateString = getDateString();

        // Get date from ISO-formatted string.
        SimpleDateFormat savedFormat = new SimpleDateFormat(Constants.PATTERN_DB_DATE, Locale.US);
        Date date = savedFormat.parse(dateString);

        return date;
    }

    public String getNotes() {
        return getString(QueryAllData.Notes);
    }

    public boolean getIsSplit() {
        int split = getInt(QueryAllData.SPLITTED);
        return split > 0;
    }

    public String getPayee() {
        return getString(QueryAllData.Payee);
    }

    public String getStatusCode() {
        return getString(QueryAllData.Status);
    }

    public TransactionStatuses getStatus() {
        String code = getStatusCode();
        return TransactionStatuses.get(code);
    }

    public String getSubcategory() {
        return getString(QueryAllData.Subcategory);
    }

    public Money getToAmount() {
        return getMoney(QueryAllData.ToAmount);
    }

    public String getTransactionTypeName() {
        return getString(QueryAllData.TransactionType);
    }

    public TransactionTypes getTransactionType() {
        String typeName = getTransactionTypeName();

        TransactionTypes transactionType = TransactionTypes.valueOf(typeName);
        return transactionType;
    }
}
