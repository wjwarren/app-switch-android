package com.venmo.appswitch.oauth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Wrapper for the Venmo OAuth API.
 *
 * @see <a href="https://developer.venmo.com/docs/oauth">Venmo OAuth API docs</a>
 * @author wijnand
 */
public class VenmoOAuth {

    /**
     * Different permissions that your users can grant your application.
     * @see <a href="https://developer.venmo.com/docs/authentication#scopes">Venmo OAuth API docs</a>
     */
    public enum Scope {
        /**
         * Make transactions (payments and charges) on that user’s behalf, within a user's weekly spending limit.
         */
        MAKE_PAYMENTS("make_payments"),

        /**
         * The Venmo feed features both a user’s payments and the payments of their friends.
         * This scope allows an application to view stories that are visible to a user’s feed.
         */
        ACCESS_FEED("access_feed"),

        /**
         * Provides access to profile information, which includes current balance and other details of a user's account.
         * Note that we do not, under any circumstance, make banking or credit card credentials visible to a third party
         * app.
         */
        ACCESS_PROFILE("access_profile"),

        /**
         * Provides access to a user's primary email address.
         */
        ACCESS_EMAIL("access_email"),

        /**
         * Provides access to a user's phone number.
         */
        ACCESS_PHONE("access_phone"),

        /**
         * Provides access to the authenticated user's balance.
         */
        ACCESS_BALANCE("access_balance"),

        /**
         * For accessing a user’s friend list.
         */
        ACCESS_FRIENDS("access_friends");

        private String mValue;

        private Scope(String value) {
            mValue = value;
        }

        /**
         * @return String - Scope value as a String.
         */
        public String getValue() {
            return mValue;
        }

    }

    private static final String VENMO_OAUTH_BASE_URL = "https://api.venmo.com/v1/oauth/authorize?client_id=%1s&scope=%1s";

    /**
     * Authenticate Venmo account using OAuth.
     * @param context {@link Context} - The context to use to launch the browser.
     * @param clientId int - Venmo client ID.
     * @param scope String - Space delimited scopes. Have a look at {@link Scope}.
     */
    public void authorize(Context context, int clientId, String scope) {
        String url = String.format(VENMO_OAUTH_BASE_URL, clientId, scope);
        url = url.replaceAll("\\+", "%20");
        Uri authUri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, authUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

}
