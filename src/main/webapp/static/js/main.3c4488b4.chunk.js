(this["webpackJsonpsecurity-v3"]=this["webpackJsonpsecurity-v3"]||[]).push([[0],{20:function(e,t,n){e.exports=n(35)},25:function(e,t,n){},26:function(e,t,n){},35:function(e,t,n){"use strict";n.r(t);var a=n(0),l=n.n(a),r=n(17),c=n.n(r),i=(n(25),n(9)),o=n(3),u=(n(26),n(12)),m=n.n(u),d="http://localhost:8080/eksamen";function s(e){return e.ok?e.json():Promise.reject({status:e.status,fullError:e.json()})}var p=function(){var e=function(e){localStorage.setItem("jwtToken",e)},t=function(){return localStorage.getItem("jwtToken")},n=function(){var e=localStorage.getItem("jwtToken").split(".")[1],t=window.atob(e);return JSON.parse(t).roles},a=function(){var e=null!=t();return e},l=function(e,n,l){var r={method:e,headers:{"Content-type":"application/json",Accept:"application/json"}};return n&&a()&&(r.headers["x-access-token"]=t()),l&&(r.body=JSON.stringify(l)),r};return{makeOptions:l,setToken:e,getToken:t,loggedIn:a,login:function(t,n){var a=l("POST",!0,{username:t,password:n});return fetch(d+"/api/login",a).then(s).then((function(t){e(t.token)}))},logout:function(){localStorage.removeItem("jwtToken")},fetchData:function(){var e=l("GET",!0);return"admin"===n()?fetch(d+"/api/info/admin",e).then(s):fetch(d+"/api/info/user",e).then(s)},getRole:n,fetchCreateWeekPlan:function(e){var t=l("POST",!0,e);return fetch(d+"/api/food/createWeekPlan/",t).then(s)},fetchAllWeekPlans:function(){var e=l("GET",!0);return fetch(d+"/api/food/allWeekPlans",e).then(s)},getUser:function(){var e=localStorage.getItem("jwtToken").split(".")[1],t=window.atob(e);return JSON.parse(t)},fetchAllRecipes:function(){var e=l("GET",!0);return fetch(d+"/api/food/all",e).then(s)},fetchCreateEditRecipe:function(e,t){if(0===e.id){var n=l("POST",!0,e);return fetch(d+"/api/food/createRecipe/",n).then(s)}var a=l("PUT",!0,e);return fetch(d+"/api/food/editRecipe/"+t,a).then(s)},fetchRecipeById:function(e){var t=l("GET",!0);return fetch(d+"/api/food/find/"+e,t).then(s)}}}(),E=(n(29),n(7)),f=n(2);function h(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function g(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?h(n,!0).forEach((function(t){Object(i.a)(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):h(n).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function v(e){var t=e.login,n=Object(a.useState)({username:"",password:""}),r=Object(o.a)(n,2),c=r[0],u=r[1];return l.a.createElement("div",null,l.a.createElement("h2",null,"Login"),l.a.createElement("form",{onChange:function(e){u(g({},c,Object(i.a)({},e.target.id,e.target.value)))}},l.a.createElement("input",{placeholder:"User Name",id:"username"}),l.a.createElement("input",{placeholder:"Password",id:"password",type:"password"}),l.a.createElement("button",{onClick:function(e){e.preventDefault(),t(c.username,c.password)}},"Login")))}var b=function(e){var t=e.logout;return l.a.createElement("div",null,l.a.createElement("h2",null,"Logout"),l.a.createElement("button",{onClick:function(){t()}},"Logout"))};function O(e){var t=e.logout;return l.a.createElement("div",null,l.a.createElement(k,null),l.a.createElement(C,{logout:t}))}var j=function(){return l.a.createElement("ul",{className:"header"},l.a.createElement("li",null,l.a.createElement(f.b,{exact:!0,activeClassName:"active",to:"/"},"Home")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/login"},"Login")))},y=function(e){var t=e.login;return l.a.createElement("div",{style:{paddingLeft:15}},l.a.createElement(E.c,null,l.a.createElement(E.a,{exact:!0,path:"/"},l.a.createElement(w,null)),l.a.createElement(E.a,{path:"/login"},l.a.createElement(v,{login:t})),l.a.createElement(E.a,{path:"*"},l.a.createElement(T,null))))},k=function(){return"admin"===p.getRole()?l.a.createElement("ul",{className:"header"},l.a.createElement("li",null,l.a.createElement(f.b,{exact:!0,activeClassName:"active",to:"/"},"Home")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/recipes"},"Recipes")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/weekplans"},"Weekplans")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/edit"},"Edit")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/logout"},"Logout")),l.a.createElement("li",{style:{float:"right"}},l.a.createElement(f.b,{activeClassName:"active",to:"/user-info"},"Hi ",p.getUser().username,"! Role: ",p.getUser().roles))):l.a.createElement("ul",{className:"header"},l.a.createElement("li",null,l.a.createElement(f.b,{exact:!0,activeClassName:"active",to:"/"},"Home")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/weekplans"},"Weekplans")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/recipes"},"Recipes")),l.a.createElement("li",null,l.a.createElement(f.b,{activeClassName:"active",to:"/logout"},"Logout")))},C=function(e){var t=e.logout;return l.a.createElement("div",{style:{paddingLeft:15}},l.a.createElement(E.c,null,l.a.createElement(E.a,{exact:!0,path:"/"},l.a.createElement(w,null)),l.a.createElement(E.a,{path:"/recipes"},l.a.createElement(D,null)),l.a.createElement(E.a,{path:"/edit"},l.a.createElement(N,null)),l.a.createElement(E.a,{path:"/weekplans"},l.a.createElement(S,null)),l.a.createElement(E.a,{path:"/logout"},l.a.createElement(b,{logout:t})),l.a.createElement(E.a,{path:"*"},l.a.createElement(T,null))))},w=function(){return l.a.createElement("div",null,l.a.createElement("h3",null,"Welcome to home"))},S=function(){var e=Object(a.useState)([]),t=Object(o.a)(e,2),n=t[0],r=t[1],c=Object(a.useState)([0]),i=Object(o.a)(c,2),u=(i[0],i[1]),d=Object(a.useState)([0]),s=Object(o.a)(d,2),E=s[0],f=s[1],h=function(e){var t=e.target.value;t.name;f(Number(t)),console.log("ID "+E)};return Object(a.useEffect)((function(){var e=!1;return p.fetchRecipeById(E).then((function(t){!1===e&&(u(t),console.log("Fetching Recipe complete",t))})),function(){e=!0}}),[E]),Object(a.useEffect)((function(){var e=!1;return p.fetchAllWeekPlans().then((function(t){!1===e&&(r(t),console.log("Fetching WeekPlans complete",t))})),function(){e=!0}}),[]),l.a.createElement("div",{style:{paddingLeft:20}},l.a.createElement("h3",null,"Weekplans"),console.log(n),l.a.createElement("table",{className:"table"},l.a.createElement("thead",null,l.a.createElement("tr",null,l.a.createElement("th",null,"Id"),l.a.createElement("th",null,"Week no."),l.a.createElement("th",null,"Year"),l.a.createElement("th",null,"Recipe ID"))),l.a.createElement("tbody",null,n.map((function(e){return l.a.createElement("tr",{key:m.a},l.a.createElement("td",null,e.weekPlanId),l.a.createElement("td",null,e.weekNo),l.a.createElement("td",null,e.year),l.a.createElement("td",null,e.recipes.map((function(e){return l.a.createElement("div",null,l.a.createElement("button",{onClick:h,value:e.id},"Click me for more info"))}))))})))))},D=function(){var e=Object(a.useState)([]),t=Object(o.a)(e,2),n=t[0],r=t[1],c=Object(a.useState)([]),i=Object(o.a)(c,2),u=i[0],d=i[1],s=Object(a.useState)({id:"",name:"",preptime:"",directions:""}),E=Object(o.a)(s,2),f=E[0],h=E[1],g=Object(a.useState)({amount:"",id:""}),v=Object(o.a)(g,2),b=v[0],O=v[1],j=Object(a.useState)({id:"",name:"",price:"",qty:""}),y=Object(o.a)(j,2),k=y[0],C=y[1],w=function(e){e.preventDefault(),console.log("event.target.value"+e.target.value),p.fetchRecipeById(e.target.value).then((function(e){h({id:e.id,name:e.name,preptime:e.preptime,directions:e.directions}),O({amount:e.ingredients[0].amount,id:e.ingredients[0].id}),C({id:e.ingredients[0].itemDTO.id,name:e.ingredients[0].itemDTO.name,price:e.ingredients[0].itemDTO.price,qty:e.ingredients[0].itemDTO.qty})})),d(f,b,k)};return Object(a.useEffect)((function(){var e=!1;return p.fetchAllRecipes().then((function(t){!1===e&&(r(t),console.log("Fetching Recipes complete",t))})),function(){e=!0}}),[]),l.a.createElement("div",{style:{paddingLeft:20}},l.a.createElement("h3",null,"Delicious Recipes"),console.log(n),l.a.createElement("table",{className:"table"},l.a.createElement("thead",null,l.a.createElement("tr",null,l.a.createElement("th",null,"Add to weekplan"),l.a.createElement("th",null,"Id"),l.a.createElement("th",null,"Name"),l.a.createElement("th",null,"Directions"),l.a.createElement("th",null,"Preptime"),l.a.createElement("th",null,"Ingredients"))),l.a.createElement("tbody",null,n.map((function(e){return l.a.createElement("tr",{key:m.a},l.a.createElement("button",{onClick:w,value:e.id},"Click me"),l.a.createElement("td",null,e.id),l.a.createElement("td",null,e.name),l.a.createElement("td",null,e.directions),l.a.createElement("td",null,"Approx. ",e.preptime," Min."),l.a.createElement("td",null,e.ingredients.map((function(e){return e.amount+" gram "+e.itemDTO.name+", "}))))})),l.a.createElement("button",{onClick:function(e){e.preventDefault(),console.log("WEEKPLAN"+u),p.fetchCreateWeekPlan(u)}},"Save weekplan"))))},N=function(){var e={id:"",name:"",preptime:"",directions:""},t={amount:"",id:""},n={id:"",name:"",price:"",qty:""},r=Object(a.useState)(0),c=Object(o.a)(r,2),u=c[0],m=c[1],d=Object(a.useState)(e),s=Object(o.a)(d,2),E=s[0],f=s[1],h=Object(a.useState)(t),v=Object(o.a)(h,2),b=v[0],O=v[1],j=Object(a.useState)(n),y=Object(o.a)(j,2),k=y[0],C=y[1],w=function(e){var t=e.target,n=t.value,a=t.id;f(g({},E,Object(i.a)({},a,n)))},S=function(e){var t=e.target,n=t.value,a=t.id;O(g({},b,Object(i.a)({},a,n)))},D=function(e){var t=e.target,n=t.value,a=t.id;C(g({},k,Object(i.a)({},a,n)))},N=null===E.id?"Submit":"Save";return l.a.createElement("div",{style:{paddingLeft:20}},l.a.createElement("h3",null,"Find and Edit"),l.a.createElement("input",{onChange:function(e){var t=e.target.value;m(Number(t)),console.log(u)},placeholder:"Find Recipe by Id"}),l.a.createElement("button",{onClick:function(e){e.preventDefault(),p.fetchRecipeById(u).then((function(e){f({id:e.id,name:e.name,preptime:e.preptime,directions:e.directions}),O({amount:e.ingredients[0].amount,id:e.ingredients[0].id}),C({id:e.ingredients[0].itemDTO.id,name:e.ingredients[0].itemDTO.name,price:e.ingredients[0].itemDTO.price,qty:e.ingredients[0].itemDTO.qty}),console.log(e)}))}},"Find Recipe"),l.a.createElement("br",null),l.a.createElement("input",{id:"name",value:E.name,onChange:w,placeholder:"Add the recipes name"}),l.a.createElement("br",null),l.a.createElement("input",{id:"preptime",value:E.preptime,onChange:w,placeholder:"Add approx preptime"}),l.a.createElement("br",null),l.a.createElement("input",{id:"directions",value:E.directions,onChange:w,placeholder:"Add helpful directions"}),l.a.createElement("br",null),l.a.createElement("input",{id:"amount",value:b.amount,onChange:S,placeholder:"Add an amount"}),l.a.createElement("br",null),l.a.createElement("input",{id:"id",value:b.id,onChange:S,placeholder:"Edit the ingredient ID"}),l.a.createElement("br",null),l.a.createElement("input",{id:"id",value:k.id,onChange:D,placeholder:"Edit the item ID"}),l.a.createElement("br",null),l.a.createElement("input",{id:"name",value:k.name,onChange:D,placeholder:"Type the ingredients name "}),l.a.createElement("br",null),l.a.createElement("input",{id:"price",value:k.price,onChange:D,placeholder:"Add a fitting price"}),l.a.createElement("br",null),l.a.createElement("input",{id:"qty",value:k.qty,onChange:D,placeholder:"Add a quantity"}),l.a.createElement("br",null),l.a.createElement("button",{onClick:function(a){a.preventDefault(),f(g({},e)),O(g({},t)),C(g({},n));var l={id:E.id,name:E.name,preptime:E.preptime,directions:E.directions,ingredients:[{amount:b.amount,id:0,itemDTO:{id:0,name:k.name,price:k.price,qty:k.qty}}]};p.fetchCreateEditRecipe(l),console.log(l)}},N))},T=function(){return l.a.createElement("div",null,"Urlen matcher ingen kendte routes")},P=function(){var e=Object(a.useState)(!1),t=Object(o.a)(e,2),n=t[0],r=t[1],c=Object(E.f)();return l.a.createElement("div",null,n?l.a.createElement("div",null,l.a.createElement(O,{logout:function(){p.logout(),r(!1),c.push("/")}})):l.a.createElement("div",null,l.a.createElement(j,null),l.a.createElement(y,{login:function(e,t){p.login(e,t).then((function(e){return r(!0)})),c.push("/")}})))};c.a.render(l.a.createElement((function(){return l.a.createElement("div",null,l.a.createElement(f.a,null,l.a.createElement(P,null)))}),null),document.getElementById("root"))}},[[20,1,2]]]);
//# sourceMappingURL=main.3c4488b4.chunk.js.map