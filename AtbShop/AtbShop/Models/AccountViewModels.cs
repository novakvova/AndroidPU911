﻿namespace AtbShop.Models
{
    public class RegisterViewModel
    {
        public string Email { get; set; }
        public string FirstName { get; set; }
        public string SecondName { get; set; }
        public string Photo { get; set; }
        public string Phone { get; set; }
        public string Password { get; set; }
        public string ConfirmPassword { get; set; }
    }
    public class LoginViewModel
    {
        /// <summary>
        /// Емейл користувача
        /// </summary>
        /// <example>admin@gmail.com</example>
        public string Email { get; set; }
        /// <summary>
        /// Пароль користувача
        /// </summary>
        /// <example>12345</example>
        public string Password { get; set; }
    }
}
