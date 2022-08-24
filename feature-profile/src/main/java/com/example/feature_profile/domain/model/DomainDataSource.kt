package com.example.feature_profile.domain.model

data class DomainDataSource(
    val id: String,
    val email: String,
    val image: String,
    val firstName: String,
    val lastName: String,
    val shippingAddresses: List<DomainShippingAddress>,
    val phoneNumber: String,
)

data class DomainShippingAddress(
    val country: String,
    val city: String,
    val street1: String,
    val street2: String,
    val state: String,
    val zip: String,
    val phone: String,
)