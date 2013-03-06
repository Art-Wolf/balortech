<?php

require_once 'includes/top.php';

?>
               <!-- Signup form : Keep the id : newsletter on form--> 
               <form class="form-horizontal" id="newsletter" action="mail.php">
                  <div class="control-group">
                     <div class="controls"> <input type="email" name="email" placeholder="Enter your email address to be notified"> <input type="submit" value="Go !"> </div>
                  </div>
               </form>
               <!-- end #signup -->

<?php

require_once 'includes/footer.php';

?>
