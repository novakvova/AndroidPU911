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
                var logger = scope.ServiceProvider.GetRequiredService<ILogger<Program>>();
                try
                {
                    InitRolesAndUsers(scope);

                } 
                catch (Exception ex)
                {
                    logger.LogError("Seeder working error "+ ex.Message);
                }

            }
        }

        private static void InitRolesAndUsers(IServiceScope scope)
        {
            var roleManager = scope.ServiceProvider.GetRequiredService<RoleManager<AppRole>>();
            var userManager = scope.ServiceProvider.GetRequiredService<UserManager<AppUser>>();
            
            if (!roleManager.Roles.Any())
            {
                var logger = scope.ServiceProvider.GetRequiredService<ILogger<RoleManager<AppRole>>>();
                
                var result = roleManager.CreateAsync(new AppRole
                {
                    Name = Roles.Admin
                }).Result;
                if (result.Succeeded)
                    logger.LogWarning("Create role " + Roles.Admin);
                else
                    logger.LogError("Faild create role " + Roles.Admin);
                result = roleManager.CreateAsync(new AppRole
                {
                    Name = Roles.User
                }).Result;
                if (result.Succeeded)
                    logger.LogWarning("Create role "+ Roles.User);
                else
                    logger.LogError("Faild create role " + Roles.Admin);
            }

            //if (!userManager.Users.Any())
            //{
            //    string email = "admin@gmail.com";
            //    var user = new AppUser
            //    {
            //        Email = email,
            //        UserName = email,
            //        FirstName = "Петро",
            //        SecondName = "Шпрот",
            //        PhoneNumber = "+38(098)232 34 22",
            //        Photo = "1.jpg"
            //    };
            //    var result = userManager.CreateAsync(user, "12345").Result;
            //    if (result.Succeeded)
            //    {
            //        result = userManager.AddToRoleAsync(user, Roles.Admin).Result;
            //    }
            //}
        }

    }
}
