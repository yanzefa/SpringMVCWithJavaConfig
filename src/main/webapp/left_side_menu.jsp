<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Left Sidebar Start -->
<div class="left side-menu">
    <div class="sidebar-inner slimscrollleft">
        <div class="user-details">
            <div class="pull-left">
                <img src="assets/images/show.png" alt="" class="thumb-md img-circle">
            </div>
            <div class="user-info">
                <div class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">John Doe <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a ><i class="md md-face-unlock"></i> Profile
                            <div class="ripple-wrapper"></div>
                        </a></li>
                        <li><a ><i class="md md-settings"></i> Settings</a></li>
                        <li><a ><i class="md md-lock"></i> Lock screen</a></li>
                        <li><a ><i class="md md-settings-power"></i> Logout</a></li>
                    </ul>
                </div>

                <p class="text-muted m-0">Administrator</p>
            </div>
        </div>
        <!--- Divider -->
        <div id="sidebar-menu">
            <ul>
                <li>
                    <a href="/" class="waves-effect waves-light"><i class="md md-home"></i><span> Dashboard </span></a>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i class="md md-mail"></i><span> Mail </span><span
                            class="pull-right"><i class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li>
                            <a url="/Log/" >日志</a>
                        </li>
                        <li><a >Inbox</a></li>
                        <li><a >Compose Mail</a></li>
                        <li><a >View Mail</a></li>
                    </ul>
                </li>

                <li>
                    <a  class="waves-effect"><i class="md md-event"></i><span> Calendar </span></a>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i class="md md-palette"></i> <span> Elements </span>
                        <span class="pull-right"><i class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a >Typography</a></li>
                        <li><a >Buttons</a></li>
                        <li><a >Panels</a></li>
                        <li><a >Checkboxs-Radios</a></li>
                        <li><a >Tabs &amp; Accordions</a></li>
                        <li><a >Modals</a></li>
                        <li><a >BS Elements</a></li>
                        <li><a >Progress Bars</a></li>
                        <li><a >Notification</a></li>
                        <li><a >Sweet-Alert</a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i
                            class="md md-invert-colors-on"></i><span> Components </span><span class="pull-right"><i
                            class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a >Grid</a></li>
                        <li><a >Portlets</a></li>
                        <li><a >Widgets</a></li>
                        <li><a >Nesteble</a></li>
                        <li><a >Sliders </a></li>
                        <li><a >Gallery </a></li>
                        <li><a >Pricing Table </a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i class="md md-redeem"></i> <span> Icons </span> <span
                            class="pull-right"><i class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a >Material Design</a></li>
                        <li><a >Ion Icons</a></li>
                        <li><a >Font awesome</a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i
                            class="md md-now-widgets"></i><span> Forms </span><span class="pull-right"><i
                            class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a >General Elements</a></li>
                        <li><a >Form Validation</a></li>
                        <li><a >Advanced Form</a></li>
                        <li><a >Form Wizard</a></li>
                        <li><a >WYSIWYG Editor</a></li>
                        <li><a >Code Editors</a></li>
                        <li><a >Multiple File Upload</a></li>
                        <li><a >X-editable</a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i
                            class="md md-view-list"></i><span> Data Tables </span><span class="pull-right"><i
                            class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a >Basic Tables</a></li>
                        <li><a >Data Table</a></li>
                        <li><a >Editable Table</a></li>
                        <li><a >Responsive Table</a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i class="md md-poll"></i><span> Charts </span><span
                            class="pull-right"><i class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a >Morris Chart</a></li>
                        <li><a >Chartjs</a></li>
                        <li><a >Flot Chart</a></li>
                        <li><a >Peity Charts</a></li>
                        <li><a >Sparkline Charts</a></li>
                        <li><a >Radial charts</a></li>
                        <li><a >Other Chart</a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i class="md md-place"></i><span> Maps </span><span
                            class="pull-right"><i class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a > Google Map</a></li>
                        <li><a > Vector Map</a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="#" class="waves-effect waves-light"><i class="md md-pages"></i><span> Pages </span><span
                            class="pull-right"><i class="md md-add"></i></span></a>
                    <ul class="list-unstyled">
                        <li><a >Profile</a></li>
                        <li><a >Timeline</a></li>
                        <li><a >Invoice</a></li>
                        <li><a >Email template</a></li>
                        <li><a >Contact-list</a></li>
                        <li><a >Login</a></li>
                        <li><a >Register</a></li>
                        <li><a >Recover Password</a></li>
                        <li><a >Lock Screen</a></li>
                        <li><a >Blank Page</a></li>
                        <li><a >Maintenance</a></li>
                        <li><a >Coming-soon</a></li>
                        <li><a >404 Error</a></li>
                        <li><a >404 alt</a></li>
                        <li><a >500 Error</a></li>
                    </ul>
                </li>

                <li class="has_sub">
                    <a href="javascript:void(0);" class="waves-effect waves-light"><i class="md md-share"></i><span>Multi Level </span><span
                            class="pull-right"><i class="md md-add"></i></span></a>
                    <ul>
                        <li class="has_sub">
                            <a href="javascript:void(0);" class="waves-effect waves-light"><span>Menu Level 1.1</span>
                                <span class="pull-right"><i class="md md-add"></i></span></a>
                            <ul style="">
                                <li><a ><span>Menu Level 2.1</span></a></li>
                                <li><a ><span>Menu Level 2.2</span></a></li>
                                <li><a ><span>Menu Level 2.3</span></a></li>
                            </ul>
                        </li>
                        <li>
                            <a ><span>Menu Level 1.2</span></a>
                        </li>
                    </ul>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!-- Left Sidebar End -->
