using AtbShop.Constants;
using AtbShop.Data.Entities.Identity;
using Microsoft.AspNetCore.Identity;

namespace AtbShop.Data
{
    public static class SeederDB
    {
        public static void SeedData(this IApplicationBuilder app)
        {
            using (var scope =
                app.ApplicationServices.GetRequiredService<IServiceScopeFactory>().CreateScope())
            {
                var roleManager = scope.ServiceProvider.GetRequiredService<RoleManager<AppRole>>();
                var userManager = scope.ServiceProvider.GetRequiredService<UserManager<AppUser>>();

                InitRolesAndUsers(roleManager, userManager);

            }
        }

        private static void InitRolesAndUsers(RoleManager<AppRole> roleManager, UserManager<AppUser> userManager)
        {
            if (!roleManager.Roles.Any())
            {
                var result = roleManager.CreateAsync(new AppRole
                {
                    Name = Roles.Admin
                }).Result;
                result = roleManager.CreateAsync(new AppRole
                {
                    Name = Roles.User
                }).Result;
            }

            if (!userManager.Users.Any())
            {
                string email = "admin@gmail.com";
                var user = new AppUser
                {
                    Email = email,
                    UserName = email,
                    FirstName = "Петро",
                    SecondName = "Шпрот",
                    PhoneNumber = "+38(098)232 34 22",
                    Photo = "1.jpg"
                };
                var result = userManager.CreateAsync(user, "12345").Result;
                if (result.Succeeded)
                {
                    result = userManager.AddToRoleAsync(user, Roles.Admin).Result;
                }
            }
        }

    }
}
