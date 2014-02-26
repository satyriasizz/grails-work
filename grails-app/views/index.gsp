<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}

            span.link {
                margin: 0;
                display: inline-block;
                background: #48802C;
                padding: 20px 5px;
            }

            span.link a {
                color: #aaaabb;
            }

            span.link:hover {
                background: #255b17;
            }
		</style>
	</head>
	<body>

		<div id="page-body" role="main">

            <sec:ifAllGranted roles="ROLE_ADMIN">
                <li>Administrator Link</li>

			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
            </sec:ifAllGranted>

            <h3>Authorization: </h3>
            <span class="link"><a href="${createLink(uri: '/registration')}">Registration</a></span>
            <span class="link"><a href="${createLink(uri: '/login')}">Sign in</a></span>
            <span class="link"><a href="${createLink(uri: '/logout')}">Logout</a></span>

            <sec:ifAllGranted roles="ROLE_WORKER"> <!-- TODO Why don`t work with admin?  (,ROLE_ADMIN)-->
            <h3>Worker menu:</h3>
            <span class="link"><a href="${createLink(uri: '/resume/create')}">Add resume</a></span>
            <span class="link"><a href="${createLink(uri: "/resume/edit/")}">Edit resume</a></span>
            <span class="link"><a href="${createLink(uri: "/resume/searchSame/")}">Show nearby resumes</a></span>
            </sec:ifAllGranted>

            <sec:ifAllGranted roles="ROLE_EMPLOYER">
            <h3>Employer menu:</h3>
            <span class="link"><a href="${createLink(uri: "/resume/list/")}">Search resume</a></span>
            </sec:ifAllGranted>
		</div>
	</body>
</html>
